package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.LoginService;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.PasswordEncryptionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;
import com.mediconnect.util.ValidationUtil;

/**
 * Servlet implementation class ChangePasswordController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ChangePassword" })
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginService loginService;
	ValidationUtil validationUtil;
	UpdateService updateService;
	RedirectionUtil redirection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordController() {
		super();
		this.loginService = new LoginService();
		this.validationUtil = new ValidationUtil();
		this.updateService = new UpdateService();
		this.redirection = new RedirectionUtil();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/ChangePassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = (String) SessionUtil.getAttribute(request, "username");
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			String reNewPassword = request.getParameter("re-newpassword");
			boolean isPasswordSame = checkPasswordSame(request, oldPassword);
			
			if (isPasswordSame == true) {
				if (validationUtil.isValidPassword(newPassword, reNewPassword)
						&& validationUtil.isPasswordSame(newPassword, reNewPassword)) {
					String newEncryptedPassword = PasswordEncryptionUtil.encrypt(newPassword);
					if (updateService.updatePassword(newEncryptedPassword, username) == null) {
						System.out.println("Error changing password");
					} else {
						redirection.redirectToPage(request, response, "index");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean checkPasswordSame(HttpServletRequest request, String oldPassword) {
		try {
			String username = (String) SessionUtil.getAttribute(request, "username");

			UserModel userModelDatabase = loginService.getUserObjectFromDatabase(username);

			String encryptedPassword = userModelDatabase.getUser_password();
			String decryptedPassword = PasswordEncryptionUtil.decrypt(encryptedPassword);

			return decryptedPassword.equals(oldPassword);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
