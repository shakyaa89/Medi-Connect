package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class UserEditProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserEditProfile" })
public class UserEditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private UpdateService updateService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditProfileController() {
        super();
        this.updateService = new UpdateService();
        this.redirectionUtil = new RedirectionUtil();
        this.extractionUtil = new ExtractionUtil();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String currentUsername = (String) session.getAttribute("username");
		int userId = updateService.getUserId(currentUsername);
		
		System.out.println(userId);
		
		UserModel userModel = extractionUtil.extractUserModelUpdate(request, response);
		Boolean isUpdated = updateService.updateUser(userModel, userId);
		
		if(isUpdated == null) {
			System.out.println("Error adding");
		}else if(isUpdated) {
			SessionUtil.setAttribute(request, "username", userModel.getUser_username());
			SessionUtil.setAttribute(request, "firstName", userModel.getUser_first_name());
			SessionUtil.setAttribute(request, "lastName", userModel.getUser_last_name());
			SessionUtil.setAttribute(request, "phoneNumber", userModel.getUser_phonenumber());
			SessionUtil.setAttribute(request, "location", userModel.getUser_location());
			SessionUtil.setAttribute(request, "DOB", userModel.getUser_dob());
			SessionUtil.setAttribute(request, "gender", userModel.getUser_gender());
			redirectionUtil.redirectToPage(request, response, "index");
			return;
		}

	}
	
//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		String currentUsername = (String) session.getAttribute("username");
//		int userId = updateService.getUserId(currentUsername);
//		
//		System.out.println(userId);
//		
//		UserModel userModel = extractionUtil.extractUserModelUpdate(request, response);
//		Boolean isUpdated = updateService.updateUser(userModel, userId);
//		
//		if(isUpdated == null) {
//			System.out.println("Error adding");
//		}else if(isUpdated) {
//			SessionUtil.setAttribute(request, "username", userModel.getUser_username());
//			SessionUtil.setAttribute(request, "firstName", userModel.getUser_first_name());
//			SessionUtil.setAttribute(request, "lastName", userModel.getUser_last_name());
//			SessionUtil.setAttribute(request, "phoneNumber", userModel.getUser_phonenumber());
//			SessionUtil.setAttribute(request, "location", userModel.getUser_location());
//			SessionUtil.setAttribute(request, "DOB", userModel.getUser_dob());
//			SessionUtil.setAttribute(request, "gender", userModel.getUser_gender());
//			redirectionUtil.redirectToPage(request, response, "index");
//			return;
//		}
//
//	}
	
	

}
