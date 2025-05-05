package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.RegisterService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.ImageUtil;
import com.mediconnect.util.RedirectionUtil;

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
	
	public void init() throws ServletException {
		this.registerService = new RegisterService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
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
		// TODO Auto-generated method stub
		try {
			UserModel userModel = extractionUtil.extractUserModelRegister(request, response);
			if(userModel == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Invalid details entered! <br> Please try again later!");
				System.out.println("Null User Model");
				request.getRequestDispatcher("register").forward(request, response);
				return;
			}
			
			Boolean isAdded = registerService.addUser(userModel);
			
			if(isAdded == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Error in our server! <br> Please try again later!");
			}else if(isAdded) {
				System.out.println("Here");
				try {
					if (extractionUtil.uploadImage(request)) {
						redirectionUtil.redirectToPage(request, response, "login");
						return;
					} else {
						redirectionUtil.setMsgAttribute(request, "error", "Error adding image <br> Please try again later!");
					}
				} catch (IOException | ServletException e) {
					redirectionUtil.setMsgAttribute(request, "error", "Error Registering <br> Please try again later!");
					e.printStackTrace(); 
				}
				
			} else {
				System.out.println("Error adding");
			}
		} catch (Exception e) {
			redirectionUtil.setMsgAttribute(request, "error", "Error Registering <br> Please try again later!");
			e.printStackTrace();
		}
		
		System.out.println("Here2");
    }
	
	

}