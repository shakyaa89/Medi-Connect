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
		
		
		if(loginStatus != null && loginStatus) {
			UserModel userModelDatabase = loginService.getUserObjectFromDatabase(username);
			String userRole = userModelDatabase.getUser_role();
			
			if(userModelDatabase == null || userRole == null) {
				System.out.println("User Object is null");
				redirectionUtil.setMsgAttribute(request, "error", "Error logging in!!");
				return;
			}
			
			SessionUtil.setAttribute(request, "username", userModelDatabase.getUser_username());
			SessionUtil.setAttribute(request, "role", userRole);
			SessionUtil.setAttribute(request, "userObj", userModelDatabase);
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
			errorMessage = "Our server is under maintenance. <br> Please try again later!";
		} else {
			errorMessage = "User credential mismatch. <br> Please try again!";
		}
		request.setAttribute("error", errorMessage);
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

}