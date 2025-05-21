package com.mediconnect.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.DashboardService;

public class ValidationUtil {
	private DashboardService dashboardService;

	public ValidationUtil() {
		// Initialize DashboardService instance
		dashboardService = new DashboardService();
	}

	// Validate password strength
	public boolean isValidPassword(String password, String retypePassword) {
		// Password must be > 6 chars, contain digit, special char, and uppercase letter
		return password.length() > 6 && password.matches(".*\\d.*")
				&& password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")
				&& password.matches(".*[A-Z].*");
	}

	// Check if password and retypePassword are the same
	public boolean isPasswordSame(String password, String retypePassword) {
		return password.equals(retypePassword);
	}

	// Validate phone number starting with 98 and 10 digits long
	public boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("^98\\d{8}$");
	}

	// Check if user is at least 18 years old based on DOB
	public boolean isAgeAtLeast16(LocalDate dob) {
		if (dob == null) {
			return false;
		}
		LocalDate today = LocalDate.now();
		return Period.between(dob, today).getYears() >= 18;
	}

	// Validate appointment date is between tomorrow and six months from today
	public boolean isAppointmentValid(LocalDate appDate) {
		if (appDate == null) {
			return false;
		}
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		LocalDate sixMonthsLater = today.plusMonths(6);

		return !appDate.isBefore(tomorrow) && !appDate.isAfter(sixMonthsLater);
	}

	// Check if entered time is within start and end times
	public boolean isTimeValid(String startTime, String endTime, String enteredTime) {
		if (startTime == null || endTime == null || enteredTime == null) {
			return false;
		}

		try {
			LocalTime startTimeCompare = LocalTime.parse(startTime);
			LocalTime endTimeCompare = LocalTime.parse(endTime);
			LocalTime enteredTimeCompare = LocalTime.parse(enteredTime);

			return !enteredTimeCompare.isBefore(startTimeCompare) && !enteredTimeCompare.isAfter(endTimeCompare);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Check if username is unique (no match in the user list)
	public boolean isUsernameDifferent(String username) {
		if (username == null) {
			return false;
		}

		List<UserModel> userList = dashboardService.getUserListAll();
		int matched = 0;

		for (UserModel user : userList) {
			if (user.getUser_username().equals(username)) {
				matched++;
			}
		}

		return matched == 0;
	}

	// Check if email is unique 
	public boolean isEmailDifferent(String email) {
		if (email == null) {
			return false;
		}

		List<UserModel> userList = dashboardService.getUserListAll();
		int matched = 0;

		for (UserModel user : userList) {
			if (user.getUser_email().equals(email)) {
				matched++;
			}
		}

		return matched == 0;
	}

	// Check if phone number is unique (no match in the user list)
	public boolean isPhoneNumDifferent(String phoneNum) {
		if (phoneNum == null) {
			return false;
		}

		List<UserModel> userList = dashboardService.getUserListAll();
		int matched = 0;

		for (UserModel user : userList) {
			if (user.getUser_phonenumber().equals(phoneNum)) {
				matched++;
			}
		}

		return matched == 0;
	}

	// Check if username is unique except for the current user (for updates)
	public boolean isUsernameDifferentUpdate(String username, int userId) {
		if (username == null) {
			return false;
		}

		List<UserModel> userList = dashboardService.getUserListAll();
		int matched = 0;

		for (UserModel user : userList) {
			if (user.getUser_id() != userId && user.getUser_username().equals(username)) {
				matched++;
			}
		}

		return matched == 0;
	}

	// Check if email is unique except for the current user (for updates)
	public boolean isEmailDifferentUpdate(String email, int userId) {
		if (email == null) {
			return false;
		}

		List<UserModel> userList = dashboardService.getUserListAll();
		int matched = 0;

		for (UserModel user : userList) {
			if (user.getUser_id() != userId && user.getUser_email().equals(email)) {
				matched++;
			}
		}

		return matched == 0;
	}

	// Check if phone number is unique except for the current user (for updates)
	public boolean isPhoneNumDifferentUpdate(String phoneNum, int userId) {
		if (phoneNum == null) {
			return false;
		}

		List<UserModel> userList = dashboardService.getUserListAll();
		int matched = 0;

		for (UserModel user : userList) {
			if (user.getUser_id() != userId && user.getUser_phonenumber().equals(phoneNum)) {
				matched++;
			}
		}

		return matched == 0;
	}
}
