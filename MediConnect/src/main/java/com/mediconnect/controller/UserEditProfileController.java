package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;
import com.mediconnect.util.ValidationUtil;

/**
 * Servlet implementation class UserEditProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserEditProfile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UserEditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UpdateService updateService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
	private ValidationUtil validationUtil;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditProfileController() {
		super();
		this.updateService = new UpdateService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
		this.validationUtil = new ValidationUtil();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String currentUsername = (String) SessionUtil.getAttribute(request, "username");
			UserModel userObj = (UserModel) SessionUtil.getAttribute(request, "userObj");
			String userRole = userObj.getUser_role();
			int userId = updateService.getUserId(currentUsername);
			String phoneNum = request.getParameter("phoneNumber");
			LocalDate dob = LocalDate.parse(request.getParameter("date-of-birth"));
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			Part imagePart = request.getPart("image");
			
			if(imagePart == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Select a profile picture!!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			if (!validationUtil.isValidPhoneNumber(phoneNum)) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid Phone Number!!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}
			
			if (!validationUtil.isAgeAtLeast16(dob)) {
				redirectionUtil.setMsgAttribute(request, "error", "Age should be atleast <br> 18 years and above!!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}
			
			if (!validationUtil.isUsernameDifferentUpdate(username, userId)) {
				redirectionUtil.setMsgAttribute(request, "error", "This username is taken!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}
			
			if (!validationUtil.isEmailDifferentUpdate(email, userId)) {
				redirectionUtil.setMsgAttribute(request, "error", "This email already exists!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}
			
			if (!validationUtil.isPhoneNumDifferentUpdate(phoneNum, userId)) {
				redirectionUtil.setMsgAttribute(request, "error", "This phone number already exists!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}
			
			
			UserModel userModel = extractionUtil.extractUserModelUpdate(request, response);
			Boolean isUpdated = updateService.updateUser(userModel, userId);

			if (isUpdated == null) {
				System.out.println("Error adding");
			} else if (isUpdated) {
				if (extractionUtil.uploadImage(request)) {
					SessionUtil.setAttribute(request, "username", userModel.getUser_username());
					SessionUtil.setAttribute(request, "userObj", userModel);
					redirectionUtil.redirectToPage(request, response, userRole + "Dashboard");
					return;
				} else {
					redirectionUtil.setMsgAttribute(request, "error",
							"Error adding image <br> Please try again later!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
