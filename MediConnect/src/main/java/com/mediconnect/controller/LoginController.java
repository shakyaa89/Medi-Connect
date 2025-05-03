package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.LoginService;
import com.mediconnect.util.CookiesUtil;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        this.loginService = new LoginService();
        this.redirectionUtil = new RedirectionUtil();
        this.extractionUtil = new ExtractionUtil();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		UserModel userModel = extractionUtil.extractUserModelLogin(request, response);
		Boolean loginStatus = loginService.loginUser(userModel);
		UserModel userModelDatabase = loginService.getUserObjectFromDatabase(username);
		String userRole = userModelDatabase.getUser_role();
		
		if(loginStatus != null && loginStatus) {
			if(userModelDatabase == null || userRole == null) {
				System.out.println("User Object is null");
				return;
			}
			SessionUtil.setAttribute(request, "username", userModelDatabase.getUser_username());
			SessionUtil.setAttribute(request, "role", userRole);
			SessionUtil.setAttribute(request, "firstName", userModelDatabase.getUser_first_name());
			SessionUtil.setAttribute(request, "lastName", userModelDatabase.getUser_last_name());
			SessionUtil.setAttribute(request, "phoneNumber", userModelDatabase.getUser_phonenumber());
			SessionUtil.setAttribute(request, "location", userModelDatabase.getUser_location());
			SessionUtil.setAttribute(request, "DOB", userModelDatabase.getUser_dob());
			SessionUtil.setAttribute(request, "gender", userModelDatabase.getUser_gender());
			SessionUtil.setAttribute(request, "email", userModelDatabase.getUser_email());
			SessionUtil.setAttribute(request, "image", userModelDatabase.getUser_image());
			if(userRole.equals("Admin")) {
				CookiesUtil.addCookie(response, "role", "Admin", 5 * 30);
				redirectionUtil.redirectToPage(request, response, "AdminDashboard");
			}else if(userRole.equals("Customer")) {
				CookiesUtil.addCookie(response, "role", "Customer", 5 * 30);
				redirectionUtil.redirectToPage(request, response, "CustomerDashboard");
			}else if(userRole.equals("Staff")) {
				CookiesUtil.addCookie(response, "role", "Staff", 5 * 30);
				redirectionUtil.redirectToPage(request, response, "StaffDashboard");
			}
		}else {
			handleLoginFailure(request, response, loginStatus);
		}
	}
	
	private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		request.setAttribute("error", errorMessage);
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

}