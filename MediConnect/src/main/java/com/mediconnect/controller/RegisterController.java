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

	public void init() throws ServletException {
		this.registerService = new RegisterService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
		this.validationUtil = new ValidationUtil();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pass = request.getParameter("password");
			String repass = request.getParameter("retypePassword");
			String phoneNum = request.getParameter("phoneNumber");
			LocalDate dob = LocalDate.parse(request.getParameter("dateOfBirth"));

			if (!validationUtil.isValidPassword(pass, repass) || !validationUtil.isPasswordSame(pass, repass)) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid Password!!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}

			if (!validationUtil.isValidPhoneNumber(phoneNum)) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid Phone Number!!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			if (!validationUtil.isAgeAtLeast16(dob)) {
				redirectionUtil.setMsgAttribute(request, "error", "Age should be atleast <br> 18 years and above!!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				return;
			}
			
			UserModel userModel = extractionUtil.extractUserModelRegister(request, response);
			if(userModel == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid details entered! <br> Please try again later!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
				System.out.println("Null User Model");
				return;
			}
			
			Boolean isAdded = registerService.addUser(userModel);
			
			if(isAdded == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Error in our server! <br> Please try again later!");
				request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			}else if(isAdded) {
				try {
					if (extractionUtil.uploadImage(request)) {
						System.out.println("Here3");
						redirectionUtil.redirectToPage(request, response, "login");
						return;
					} else {
						redirectionUtil.setMsgAttribute(request, "error", "Error adding image <br> Please try again later!");
						request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
					}
				} catch (Exception e) {
					redirectionUtil.setMsgAttribute(request, "error", "Error Registering <br> Please try again later!");
					request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
					e.printStackTrace(); 
				}
			} else {
				System.out.println("Error adding");
			}
		} catch (Exception e) {
			redirectionUtil.setMsgAttribute(request, "error", "Error Registering <br> Please try again later!");
			request.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(request, response);
			e.printStackTrace();
		}
}
	
	

}