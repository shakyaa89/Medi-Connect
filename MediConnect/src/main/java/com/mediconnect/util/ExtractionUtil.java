package com.mediconnect.util;

import java.io.IOException;
import java.time.LocalDate;

import com.mediconnect.model.UserModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.AppointmentModel;
import com.mediconnect.model.DoctorAvailabilityModel;
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
	private RedirectionUtil redirectionUtil;
	// Path for saving images in project directory
	private static final String imageDirectory = "C:/Users/shash/git/Medi-Connect/MediConnect/src/main/webapp/";

	public ExtractionUtil() {
		// Initialize service and utility classes
		this.updateService = new UpdateService();
		this.validationUtil = new ValidationUtil();
		this.imageUtil = new ImageUtil();
		this.redirectionUtil = new RedirectionUtil();
	}

	// Extracts UserModel from login form parameters
	public UserModel extractUserModelLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		return new UserModel(username, password);
	}

	// Extracts updated UserModel including image, for logged in user
	public UserModel extractUserModelUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		Part image = request.getPart("image");
		String imageUrl = imageUtil.getImageNameFromPart(image);

		return new UserModel(userId, name, lastName, username, email, phoneNum, gender, location, birthday, imageUrl);
	}

	// Extracts UserModel from registration form; validates password and phone
	// number
	public UserModel extractUserModelRegister(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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

		// Validate password and phone number, return null on failure
		if (!validationUtil.isValidPassword(pass, repass) || !validationUtil.isPasswordSame(pass, repass)) {
			return null;
		} else if (!validationUtil.isValidPhoneNumber(phoneNum)) {
			return null;
		} else {
			pass = PasswordEncryptionUtil.encrypt(pass);
			return new UserModel(null, name, lastName, username, email, phoneNum, gender, birthday, location, pass,
					"Customer", imageUrl);
		}
	}

	// Upload user profile image to both servlet context and project directory
	public boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("image");
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "Profiles")
				&& imageUtil.uploadImageProjectDirectory(image, imageDirectory, "Profiles");
	}

	// Upload doctor profile image to servlet context and project directory
	public boolean uploadDoctorImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("doctor-image");
		System.out.println(req.getServletContext().getRealPath("/"));
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "DoctorProfiles")
				&& imageUtil.uploadImageProjectDirectory(image, imageDirectory, "DoctorProfiles");
	}

	// Upload staff profile image to servlet context and project directory
	public boolean uploadStaffImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("staff-image");
		System.out.println(req.getServletContext().getRealPath("/"));
		return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "Profiles")
				&& imageUtil.uploadImageProjectDirectory(image, imageDirectory, "Profiles");
	}

	// Extract DoctorModel from request; validates phone number
	public DoctorModel extractDoctorModel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String location = request.getParameter("location");
		String email = request.getParameter("email");
		String specialization = request.getParameter("specialization");
		String experience = request.getParameter("experience");
		String phoneNum = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");

		Part image = request.getPart("doctor-image");
		String imageUrl = imageUtil.getImageNameFromPart(image);

		if (!validationUtil.isValidPhoneNumber(phoneNum)) {
			redirectionUtil.setMsgAttribute(request, "error", "Invalid Phonenumber!!");
			System.out.println("Error Phonenumber");
			return null;
		} else {
			return new DoctorModel(name, lastName, email, phoneNum, location, gender, specialization, experience,
					imageUrl);
		}

	}

	// Extract DoctorAvailabilityModel from request parameters representing time and
	// availability days
	public DoctorAvailabilityModel extractDoctorAvailabilityModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String startTime = request.getParameter("start-time");
		String endTime = request.getParameter("end-time");
		String weekDays = request.getParameter("WeekDays");
		String weekEnd = request.getParameter("WeekEnd");

		String availableDays = "";

		if (weekDays != null && weekEnd != null) {
			availableDays = "Week Days, Week End";
		} else if (weekDays != null) {
			availableDays = "Week Days";
		} else if (weekEnd != null) {
			availableDays = "Week End";
		}

		return new DoctorAvailabilityModel(startTime, endTime, availableDays);
	}

	// Extract Staff UserModel; validate password, encrypt it, and get image
	public UserModel extractStaffModel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String location = request.getParameter("location");
		String email = request.getParameter("email");
		LocalDate birthday = LocalDate.parse(request.getParameter("date-of-birth"));
		String phoneNum = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");

		String pass = request.getParameter("password");
		String repass = request.getParameter("retype-password");

		Part image = request.getPart("staff-image");
		String imageUrl = imageUtil.getImageNameFromPart(image);

		// Log error if password invalid but proceed anyway
		if (!validationUtil.isValidPassword(pass, repass) || !validationUtil.isPasswordSame(pass, repass)) {
			System.out.println("error password");
		}

		pass = PasswordEncryptionUtil.encrypt(pass);

		return new UserModel(null, name, lastName, username, email, phoneNum, gender, birthday, location, pass, "Staff",
				imageUrl);
	}

	// Extract updated Staff UserModel including image
	public UserModel extractStaffModelUpdate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String location = request.getParameter("location");
		String email = request.getParameter("email");
		LocalDate birthday = LocalDate.parse(request.getParameter("date-of-birth"));
		String phoneNum = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");

		Part image = request.getPart("staff-image");
		String imageUrl = imageUtil.getImageNameFromPart(image);

		return new UserModel(name, lastName, username, email, phoneNum, gender, location, imageUrl, birthday);
	}

	// Extract AppointmentModel with status "Confirmed"
	public AppointmentModel extractAppointmentModel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LocalDate appointmentDate = LocalDate.parse(request.getParameter("date"));
		String time = request.getParameter("time");

		return new AppointmentModel(null, appointmentDate, time, "Confirmed");
	}

	// Extract AppointmentModel update (without status)
	public AppointmentModel extractAppointmentModelUpdate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LocalDate appointmentDate = LocalDate.parse(request.getParameter("date"));
		String time = request.getParameter("time");

		return new AppointmentModel(appointmentDate, time);
	}
}
