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
 * Handles user profile editing and updating including validation and image upload.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserEditProfile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UserEditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service and utility objects for update, redirection, extraction, and validation
	private UpdateService updateService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
	private ValidationUtil validationUtil;

	/**
	 * Default constructor initializing service/utilities.
	 */
	public UserEditProfileController() {
		super();
		this.updateService = new UpdateService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
		this.validationUtil = new ValidationUtil();
	}

	/**
	 * Handles GET requests - forwards to UserEditProfile JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests - delegates to doPut for update processing.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(request, response);
	}

	/**
	 * Handles PUT requests - processes user profile update.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Retrieve current username and user object from session
			String currentUsername = (String) SessionUtil.getAttribute(request, "username");
			UserModel userObj = (UserModel) SessionUtil.getAttribute(request, "userObj");
			String userRole = userObj.getUser_role();

			// Get user ID by current username
			int userId = updateService.getUserId(currentUsername);

			// Extract form parameters
			String phoneNum = request.getParameter("phoneNumber");
			LocalDate dob = LocalDate.parse(request.getParameter("date-of-birth"));
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			Part imagePart = request.getPart("image");

			// Validate presence of image part
			if(imagePart == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Select a profile picture!!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			// Validate phone number format
			if (!validationUtil.isValidPhoneNumber(phoneNum)) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid Phone Number!!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			// Validate age >= 18 years
			if (!validationUtil.isAgeAtLeast16(dob)) {
				redirectionUtil.setMsgAttribute(request, "error", "Age should be atleast <br> 18 years and above!!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			// Validate username uniqueness excluding current user
			if (!validationUtil.isUsernameDifferentUpdate(username, userId)) {
				redirectionUtil.setMsgAttribute(request, "error", "This username is taken!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			// Validate email uniqueness excluding current user
			if (!validationUtil.isEmailDifferentUpdate(email, userId)) {
				redirectionUtil.setMsgAttribute(request, "error", "This email already exists!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			// Validate phone number uniqueness excluding current user
			if (!validationUtil.isPhoneNumDifferentUpdate(phoneNum, userId)) {
				redirectionUtil.setMsgAttribute(request, "error", "This phone number already exists!");
				request.getRequestDispatcher("WEB-INF/pages/UserEditProfile.jsp").forward(request, response);
				return;
			}

			// Extract updated user model from request
			UserModel userModel = extractionUtil.extractUserModelUpdate(request, response);

			// Attempt to update user data
			Boolean isUpdated = updateService.updateUser(userModel, userId);

			if (isUpdated == null) {
				System.out.println("Error adding");
			} else if (isUpdated) {
				// Upload profile image if update successful
				if (extractionUtil.uploadImage(request)) {
					// Update session attributes with new user info
					SessionUtil.setAttribute(request, "username", userModel.getUser_username());
					SessionUtil.setAttribute(request, "userObj", userModel);
					// Redirect to appropriate dashboard based on role
					redirectionUtil.redirectToPage(request, response, userRole + "Dashboard");
					return;
				} else {
					// Show error if image upload fails
					redirectionUtil.setMsgAttribute(request, "error",
							"Error adding image <br> Please try again later!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
