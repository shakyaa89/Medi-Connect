package com.mediconnect.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.AppointmentModel;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.UserModel;

public class UpdateService {

	private Connection dbConnection;

	// Initialize DB connection in constructor
	public UpdateService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// Update user information by userId
	public Boolean updateUser(UserModel userModel, int userId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}

		String updateQuery = "UPDATE users SET user_first_name = ?, user_last_name = ?, user_username = ?, user_email = ?, user_phonenumber = ?, user_gender = ?, user_dob = ?, user_location = ?, user_image = ? WHERE user_id = ?;";

		try {
			dbConnection.setAutoCommit(false);

			PreparedStatement insertStmt = dbConnection.prepareStatement(updateQuery);
			insertStmt.setString(1, userModel.getUser_first_name());
			insertStmt.setString(2, userModel.getUser_last_name());
			insertStmt.setString(3, userModel.getUser_username());
			insertStmt.setString(4, userModel.getUser_email());
			insertStmt.setString(5, userModel.getUser_phonenumber());
			insertStmt.setString(6, userModel.getUser_gender());
			insertStmt.setDate(7, Date.valueOf(userModel.getUser_dob()));
			insertStmt.setString(8, userModel.getUser_location());
			insertStmt.setString(9, userModel.getUser_image());
			insertStmt.setInt(10, userId);

			int isUpdated = insertStmt.executeUpdate();
			dbConnection.commit();
			return isUpdated > 0;

		} catch (SQLException e) {
			System.err.println("SQL Error");
			e.printStackTrace();
			return null;
		}
	}

	// Update doctor info and availability by doctorId
	public Boolean updateDoctor(DoctorModel doctorModel, DoctorAvailabilityModel doctorAvModel, int doctorId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}

		String updateDoctorQuery = "UPDATE doctors SET doctor_first_name = ?, doctor_last_name = ?, doctor_email = ?, doctor_phonenumber = ?, doctor_address = ?, doctor_gender = ?, doctor_specialization = ?, doctor_experience = ?, doctor_image = ? WHERE doctor_id = ?";
		String doctorAvailabilityUpdateQuery = "UPDATE doctor_availability SET start_time = ?, end_time = ?, doctor_available_day = ? WHERE doctor_id = ?";

		try {
			PreparedStatement insertStmtDoctor = dbConnection.prepareStatement(updateDoctorQuery);
			insertStmtDoctor.setString(1, doctorModel.getDoctorFirstName());
			insertStmtDoctor.setString(2, doctorModel.getDoctorLastName());
			insertStmtDoctor.setString(3, doctorModel.getDoctorEmail());
			insertStmtDoctor.setString(4, doctorModel.getDoctorPhoneNumber());
			insertStmtDoctor.setString(5, doctorModel.getDoctorAddress());
			insertStmtDoctor.setString(6, doctorModel.getDoctorGender());
			insertStmtDoctor.setString(7, doctorModel.getDoctorSpecialization());
			insertStmtDoctor.setString(8, doctorModel.getDoctorExperience());
			insertStmtDoctor.setString(9, doctorModel.getDoctorImage());
			insertStmtDoctor.setInt(10, doctorId);

			int isUpdated = insertStmtDoctor.executeUpdate();

			if (isUpdated > 0) {
				PreparedStatement availabilityStmt = dbConnection.prepareStatement(doctorAvailabilityUpdateQuery);
				availabilityStmt.setString(1, doctorAvModel.getStart_time());
				availabilityStmt.setString(2, doctorAvModel.getEnd_time());
				availabilityStmt.setString(3, doctorAvModel.getDoctor_available_day());
				availabilityStmt.setInt(4, doctorId);

				return availabilityStmt.executeUpdate() > 0;
			} else {
				System.out.println("Cannot add doctor!");
				return null;
			}

		} catch (SQLException e) {
			System.err.println("SQL Error");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieve userId by username
	public Integer getUserId(String username) {
		if (dbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		String fetchUserIdQuery = "SELECT user_id FROM users WHERE user_username = ?";
		ResultSet results = null;

		try {
			PreparedStatement fetchUserRoleStmt = dbConnection.prepareStatement(fetchUserIdQuery);
			fetchUserRoleStmt.setString(1, username);
			results = fetchUserRoleStmt.executeQuery();

			if (results.next()) {
				return results.getInt("user_id");
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.print("Error Fetching User Id");
			return null;
		}
	}

	// Update user's password by username
	public Boolean updatePassword(String newPassword, String username) {
		if (dbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		String updateQuery = "UPDATE users SET user_password = ? WHERE user_username = ?";

		try {
			PreparedStatement insertStmt = dbConnection.prepareStatement(updateQuery);
			insertStmt.setString(1, newPassword);
			insertStmt.setString(2, username);

			return insertStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("SQL Error");
			e.printStackTrace();
			return null;
		}

	}

	// Update appointment details by appointmentId
	public Boolean updateAppointment(AppointmentModel appointmentModel, int appointmentId) {
		if (dbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		String updateQuery = "UPDATE appointment SET appointment_date = ?, appointment_time = ? WHERE appointment_id = ?";

		try {
			PreparedStatement updateStmt = dbConnection.prepareStatement(updateQuery);
			updateStmt.setDate(1, Date.valueOf(appointmentModel.getAppointment_date()));
			updateStmt.setString(2, appointmentModel.getAppointment_time());
			updateStmt.setInt(3, appointmentId);

			return updateStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("SQL Error");
			e.printStackTrace();
			return null;
		}

	}

	// Update staff user info by staffId
	public Boolean updateStaff(UserModel UserModel, int staffId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}

		String updateQuery = "UPDATE users SET user_first_name = ?, user_last_name = ?, user_username = ?, user_email = ?, user_phonenumber = ?, user_gender = ?, user_dob = ?, user_location = ?, user_image = ? WHERE user_id = ?";

		try {
			PreparedStatement updateStmt = dbConnection.prepareStatement(updateQuery);
			updateStmt.setString(1, UserModel.getUser_first_name());
			updateStmt.setString(2, UserModel.getUser_last_name());
			updateStmt.setString(3, UserModel.getUser_username());
			updateStmt.setString(4, UserModel.getUser_email());
			updateStmt.setString(5, UserModel.getUser_phonenumber());
			updateStmt.setString(6, UserModel.getUser_gender());
			updateStmt.setDate(7, Date.valueOf(UserModel.getUser_dob()));
			updateStmt.setString(8, UserModel.getUser_location());
			updateStmt.setString(9, UserModel.getUser_image());
			updateStmt.setInt(10, staffId);

			return updateStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Error");
			e.printStackTrace();
			return null;
		}
	}
}
