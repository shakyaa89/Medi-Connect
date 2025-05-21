package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.RegisterService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.ImageUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.ValidationUtil;

/**
 * Servlet implementation class RegisterController
 * Handles user registration including validation, user creation, and image upload.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50)
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RegisterService registerService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
	private ValidationUtil validationUtil;

	/**
	 * Servlet initialization: instantiate service and utility objects.
	 */
	public void init() throws ServletException {
		this.registerService = new RegisterService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
		this.validationUtil = new ValidationUtil();
	}
       
    /**
     * Default constructor.
     */
    public RegisterController() {
        super();
    }

	/**
	 * Handles GET requests to show the registration page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to process registration form submission.
	 * Validates input, adds user, and handles profile image upload.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Extract parameters from request
			String pass = request.getParameter("password");
			String repass = request.getParameter("retypePassword");
			String phoneNum = request.getParameter("phoneNumber");
			LocalDate dob = LocalDate.parse(request.getParameter("dateOfBirth"));
			String username = request.getParameter("username");
			String email = request.getParameter("email");

			// Validate password format and confirmation
			if (!validationUtil.isValidPassword(pass, repass) || !validationUtil.isPasswordSame(pass, repass)) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid Password!!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}

			// Validate phone number format
			if (!validationUtil.isValidPhoneNumber(phoneNum)) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid Phone Number!!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			// Validate minimum age requirement (at least 18)
			if (!validationUtil.isAgeAtLeast16(dob)) {
				redirectionUtil.setMsgAttribute(request, "error", "Age should be atleast <br> 18 years and above!!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			// Check if username is already taken
			if (!validationUtil.isUsernameDifferent(username)) {
				redirectionUtil.setMsgAttribute(request, "error", "This username is taken!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			// Check if email already exists
			if (!validationUtil.isEmailDifferent(email)) {
				redirectionUtil.setMsgAttribute(request, "error", "This email already exists!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			// Check if phone number already exists
			if (!validationUtil.isPhoneNumDifferent(phoneNum)) {
				redirectionUtil.setMsgAttribute(request, "error", "This phone number already exists!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			// Extract UserModel from request parameters
			UserModel userModel = extractionUtil.extractUserModelRegister(request, response);
			if(userModel == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid details entered! <br> Please try again later!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				System.out.println("Null User Model");
				return;
			}
			
			// Attempt to add user to database
			Boolean isAdded = registerService.addUser(userModel);
			
			if(isAdded == null) {
				// Server error during user addition
				redirectionUtil.setMsgAttribute(request, "error", "Error in our server! <br> Please try again later!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			}else if(isAdded) {
				// If user added successfully, try to upload profile image
				try {
					if (extractionUtil.uploadImage(request)) {
						System.out.println("Here3");
						redirectionUtil.redirectToPage(request, response, "login");
						return;
					} else {
						// Image upload failed
						redirectionUtil.setMsgAttribute(request, "error", "Error adding image <br> Please try again later!");
						request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
					}
				} catch (Exception e) {
					// Exception during image upload
					redirectionUtil.setMsgAttribute(request, "error", "Error Registering <br> Please try again later!");
					request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
					e.printStackTrace(); 
				}
			} else {
				System.out.println("Error adding");
			}
		} catch (Exception e) {
			// General exception handling
			redirectionUtil.setMsgAttribute(request, "error", "Error Registering <br> Please try again later!");
			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}
