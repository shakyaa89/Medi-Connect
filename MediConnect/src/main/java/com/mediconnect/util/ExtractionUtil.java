package com.mediconnect.util;

import java.io.IOException;
import java.time.LocalDate;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.UpdateService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ExtractionUtil {
	
	private UpdateService updateService;
	private ValidationUtil validationUtil;
	private ImageUtil imageUtil;
	
	public ExtractionUtil() {
		this.updateService = new UpdateService();
		this.validationUtil = new ValidationUtil();
		this.imageUtil = new ImageUtil();
	}
	
	public UserModel extractUserModelLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		return new UserModel(username, password);
	}
	
	public UserModel extractUserModelUpdate(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String currentUsername = (String) session.getAttribute("username");
		
		String name = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String location = request.getParameter("location");
		String email = request.getParameter("email");
		LocalDate birthday = LocalDate.parse(request.getParameter("date-of-birth"));
		String phoneNum = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		
		int userId = updateService.getUserId(currentUsername);
		
		return new UserModel(userId, name, lastName, username, email, phoneNum, gender, birthday, location);
	}
	
	public UserModel extractUserModelRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String name = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String location = request.getParameter("location");
		String email = request.getParameter("email");
		LocalDate birthday = LocalDate.parse(request.getParameter("dateOfBirth"));
		String phoneNum = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		
		String pass = request.getParameter("password");
		String repass = request.getParameter("retypePassword");
		
		Part image = request.getPart("image");
		String imageUrl = imageUtil.getImageNameFromPart(image);
		
		if(!validationUtil.isValidPassword(pass, repass) || !validationUtil.isPasswordSame(pass, repass)) {
			System.out.println("error password");
		}

		pass = PasswordEncryptionUtil.encrypt(pass);
		
		return new UserModel(null, name, lastName, username, email, phoneNum, gender, birthday, location, pass, "Customer", imageUrl);
	}
	
	public boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("image");
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "Profiles");
	}
}
