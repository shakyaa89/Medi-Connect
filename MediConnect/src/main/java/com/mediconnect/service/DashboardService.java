package com.mediconnect.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.AppointmentListModel;
import com.mediconnect.model.AppointmentModel;
import com.mediconnect.model.DashboardInfoModel;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.UserModel;

public class DashboardService {

	private Connection DbConnection;

	// Constructor initializes database connection
	public DashboardService() {
		try {
			DbConnection = Dbconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}

	// Retrieves list of users with role 'Customer'
	public List<UserModel> getUserList() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		List<UserModel> userList = new ArrayList<UserModel>();

		String fetchDoctorQuery = "SELECT * FROM users WHERE user_role = 'Customer'";

		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			ResultSet results = fetchStmt.executeQuery();

			while (results.next()) {
				// Extract user details from result set
				Integer userId = results.getInt("user_id");
				String userFirstName = results.getString("user_first_name");
				String userLastName = results.getString("user_last_name");
				String userUsername = results.getString("user_username");
				String userEmail = results.getString("user_email");
				String userPhoneNumber = results.getString("user_phonenumber");
				String userGender = results.getString("user_gender");
				LocalDate userDOB = LocalDate.parse(results.getString("user_dob"));
				String userAddress = results.getString("user_location");

				// Create UserModel object and add to list
				UserModel userObj = new UserModel(userId, userFirstName, userLastName, userUsername, userEmail,
						userPhoneNumber, userGender, userDOB, userAddress);
				userList.add(userObj);
			}

			return userList;

		} catch (SQLException e) {
			System.out.println("Error creating user list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves list of all users regardless of role
	public List<UserModel> getUserListAll() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		List<UserModel> userList = new ArrayList<UserModel>();
		String fetchUserQuery = "SELECT * FROM users;";

		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchUserQuery);
			ResultSet results = fetchStmt.executeQuery();

			while (results.next()) {
				// Extract user details
				Integer userId = results.getInt("user_id");
				String userFirstName = results.getString("user_first_name");
				String userLastName = results.getString("user_last_name");
				String userUsername = results.getString("user_username");
				String userEmail = results.getString("user_email");
				String userPhoneNumber = results.getString("user_phonenumber");
				String userGender = results.getString("user_gender");
				LocalDate userDOB = LocalDate.parse(results.getString("user_dob"));
				String userAddress = results.getString("user_location");

				// Add user to list
				UserModel userObj = new UserModel(userId, userFirstName, userLastName, userUsername, userEmail,
						userPhoneNumber, userGender, userDOB, userAddress);
				userList.add(userObj);
			}

			return userList;

		} catch (SQLException e) {
			System.out.println("Error creating user list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves list of all doctors
	public List<DoctorModel> getDoctorList() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
		String fetchDoctorQuery = "SELECT * FROM doctors";

		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			ResultSet results = fetchStmt.executeQuery();

			while (results.next()) {
				// Extract doctor details
				Integer doctorId = results.getInt("doctor_id");
				String doctorFirstName = results.getString("doctor_first_name");
				String doctorLastName = results.getString("doctor_last_name");
				String doctorEmail = results.getString("doctor_email");
				String doctorPhoneNumber = results.getString("doctor_phonenumber");
				String doctorAddress = results.getString("doctor_address");
				String doctorGender = results.getString("doctor_gender");
				String doctorSpecialization = results.getString("doctor_specialization");
				String doctorExperience = results.getString("doctor_experience");
				String doctorImage = results.getString("doctor_image");

				// Create DoctorModel object and add to list
				DoctorModel doctorObj = new DoctorModel(doctorId, doctorFirstName, doctorLastName, doctorEmail,
						doctorPhoneNumber, doctorAddress, doctorGender, doctorSpecialization, doctorExperience,
						doctorImage);
				doctorList.add(doctorObj);
			}

			return doctorList;

		} catch (SQLException e) {
			System.out.println("Error creating doctor list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves list of doctor availability entries
	public List<DoctorAvailabilityModel> getDoctorAvailabilityList() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		List<DoctorAvailabilityModel> doctorAvailabilityList = new ArrayList<DoctorAvailabilityModel>();
		String fetchDoctorQuery = "SELECT * FROM doctor_availability";

		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			ResultSet results = fetchStmt.executeQuery();

			while (results.next()) {
				// Extract availability details
				Integer doctorAvailablilityId = results.getInt("doctor_availability_id");
				Integer doctorId = results.getInt("doctor_id");
				String doctorStartTime = results.getString("start_time");
				String doctorEndTime = results.getString("end_time");
				String doctorAvailableDay = results.getString("doctor_available_day");

				// Create DoctorAvailabilityModel object and add to list
				DoctorAvailabilityModel doctorObj = new DoctorAvailabilityModel(doctorAvailablilityId, doctorId,
						doctorStartTime, doctorEndTime, doctorAvailableDay);
				doctorAvailabilityList.add(doctorObj);
			}

			return doctorAvailabilityList;

		} catch (SQLException e) {
			System.out.println("Error creating doctor list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves list of users with role 'Staff'
	public List<UserModel> getStaffList() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		List<UserModel> userList = new ArrayList<UserModel>();
		String fetchDoctorQuery = "SELECT * FROM users WHERE user_role = 'Staff'";

		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			ResultSet results = fetchStmt.executeQuery();

			while (results.next()) {
				// Extract staff details
				Integer staffId = results.getInt("user_id");
				String staffFirstName = results.getString("user_first_name");
				String staffLastName = results.getString("user_last_name");
				String staffUsername = results.getString("user_username");
				String staffEmail = results.getString("user_email");
				String staffPhoneNumber = results.getString("user_phonenumber");
				String staffGender = results.getString("user_gender");
				LocalDate staffDOB = LocalDate.parse(results.getString("user_dob"));
				String staffAddress = results.getString("user_location");

				// Add staff user to list
				UserModel userObj = new UserModel(staffId, staffFirstName, staffLastName, staffUsername, staffEmail,
						staffPhoneNumber, staffGender, staffDOB, staffAddress);
				userList.add(userObj);
			}

			return userList;

		} catch (SQLException e) {
			System.out.println("Error creating user list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves list of appointments with joined user and doctor info
	public List<AppointmentListModel> getAppointmentListModel() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		List<AppointmentListModel> userList = new ArrayList<AppointmentListModel>();

		String fetchAppointmentList = "SELECT ap.appointment_id, u.user_id, u.user_first_name, u.user_last_name, d.doctor_id, d.doctor_first_name, d.doctor_last_name, ap.appointment_time, ap.appointment_date FROM appointment ap JOIN doctor_user_appointment dua ON ap.appointment_id = dua.appointment_id JOIN users u ON dua.user_id = u.user_id JOIN doctors d ON dua.doctor_id = d.doctor_id;";

		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchAppointmentList);
			ResultSet results = fetchStmt.executeQuery();

			while (results.next()) {
				// Extract appointment, user, and doctor details
				int appointmentId = results.getInt("appointment_id");
				int userId = results.getInt("user_id");
				int doctorId = results.getInt("doctor_id");
				String userFirstName = results.getString("user_first_name");
				String userLastName = results.getString("user_last_name");
				String doctorFirstName = results.getString("doctor_first_name");
				String doctorLastName = results.getString("doctor_last_name");
				String appointmentTime = results.getString("appointment_time");
				LocalDate appointmentDate = LocalDate.parse(results.getString("appointment_date"));

				// Create AppointmentListModel and add to list
				AppointmentListModel userObj = new AppointmentListModel(appointmentId, userId, doctorId, userFirstName,
						userLastName, doctorFirstName, doctorLastName, appointmentTime, appointmentDate);
				userList.add(userObj);
			}

			return userList;

		} catch (SQLException e) {
			System.out.println("Error creating user list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves appointment date and time for a specific appointment by id
	public AppointmentModel getAppointmentModelUpdate(int appointmentId) {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		String fetchAppointmentList = "SELECT appointment_date, appointment_time FROM appointment WHERE appointment_id = ?";
		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchAppointmentList);
			fetchStmt.setInt(1, appointmentId);
			ResultSet results = fetchStmt.executeQuery();

			if (results.next()) {
				String appointmentTime = results.getString("appointment_time");
				LocalDate appointmentDate = LocalDate.parse(results.getString("appointment_date"));

				// Return appointment model with date and time
				AppointmentModel appointmentObj = new AppointmentModel(appointmentDate, appointmentTime);
				return appointmentObj;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Error creating appointment list!");
			e.printStackTrace();
			return null;
		}
	}

	// Retrieves dashboard summary info for admin with counts of doctors, staff,
	// appointments, customers, and gender breakdowns
	public DashboardInfoModel getAdminDashboardInfo() {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			// Count doctors
			String fetchNumOfDoctor = "SELECT COUNT(*) AS numOfDoctor FROM doctors";
			stmt = DbConnection.prepareStatement(fetchNumOfDoctor);
			result = stmt.executeQuery();
			int numOfDoctor = 0;
			if (result.next()) {
				numOfDoctor = result.getInt("numOfDoctor");
			}

			// Count staff
			String fetchNumOfStaff = "SELECT COUNT(*) AS numOfStaff FROM users WHERE user_role = 'Staff'";
			stmt = DbConnection.prepareStatement(fetchNumOfStaff);
			result = stmt.executeQuery();
			int numOfStaff = 0;
			if (result.next()) {
				numOfStaff = result.getInt("numOfStaff");
			}

			// Count appointments
			String fetchNumOfAppointment = "SELECT COUNT(*) AS numOfAppointment FROM appointment";
			stmt = DbConnection.prepareStatement(fetchNumOfAppointment);
			result = stmt.executeQuery();
			int numOfAppointment = 0;
			if (result.next()) {
				numOfAppointment = result.getInt("numOfAppointment");
			}

			// Count customers
			String fetchNumOfCustomer = "SELECT COUNT(*) AS numOfCustomer FROM users WHERE user_role = 'Customer'";
			stmt = DbConnection.prepareStatement(fetchNumOfCustomer);
			result = stmt.executeQuery();
			int numOfCustomer = 0;
			if (result.next()) {
				numOfCustomer = result.getInt("numOfCustomer");
			}

			// Count male customers
			String fetchNumOfMaleCustomer = "SELECT COUNT(*) AS numOfMaleCustomer FROM users WHERE user_role = 'Customer' AND user_gender = 'Male'";
			stmt = DbConnection.prepareStatement(fetchNumOfMaleCustomer);
			result = stmt.executeQuery();
			int numOfMaleCustomer = 0;
			if (result.next()) {
				numOfMaleCustomer = result.getInt("numOfMaleCustomer");
			}

			// Count female customers
			String fetchNumOfFemaleCustomer = "SELECT COUNT(*) AS numOfFemaleCustomer FROM users WHERE user_role = 'Customer' AND user_gender = 'Female'";
			stmt = DbConnection.prepareStatement(fetchNumOfFemaleCustomer);
			result = stmt.executeQuery();
			int numOfFemaleCustomer = 0;
			if (result.next()) {
				numOfFemaleCustomer = result.getInt("numOfFemaleCustomer");
			}

			// Count male staff
			String fetchNumOfMaleStaff = "SELECT COUNT(*) AS numOfMaleStaff FROM users WHERE user_role = 'Staff' AND user_gender = 'Male'";
			stmt = DbConnection.prepareStatement(fetchNumOfMaleStaff);
			result = stmt.executeQuery();
			int numOfMaleStaff = 0;
			if (result.next()) {
				numOfMaleStaff = result.getInt("numOfMaleStaff");
			}

			// Count female staff
			String fetchNumOfFemaleStaff = "SELECT COUNT(*) AS numOfFemaleStaff FROM users WHERE user_role = 'Staff' AND user_gender = 'Female'";
			stmt = DbConnection.prepareStatement(fetchNumOfFemaleStaff);
			result = stmt.executeQuery();
			int numOfFemaleStaff = 0;
			if (result.next()) {
				numOfFemaleStaff = result.getInt("numOfFemaleStaff");
			}

			// Create DashboardInfoModel with all counts
			DashboardInfoModel dashboardObj = new DashboardInfoModel(numOfDoctor, numOfStaff, numOfAppointment,
					numOfCustomer, numOfMaleCustomer, numOfFemaleCustomer, numOfMaleStaff, numOfFemaleStaff);
			return dashboardObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Retrieves dashboard summary info for a customer, including number of
	// appointments and total doctors count
	public DashboardInfoModel customerDashboardModel(int userId) {
		if (DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}

		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			// Count appointments for specific user
			String fetchNumOfAppointments = "SELECT COUNT(*) AS numOfAppointment FROM doctor_user_appointment WHERE user_id = ?";
			stmt = DbConnection.prepareStatement(fetchNumOfAppointments);
			stmt.setInt(1, userId);
			result = stmt.executeQuery();
			int numOfAppointment = 0;
			if (result.next()) {
				numOfAppointment = result.getInt("numOfAppointment");
			}

			// Count total doctors
			String fetchNumOfDoctor = "SELECT COUNT(*) AS numOfDoctor FROM doctors";
			stmt = DbConnection.prepareStatement(fetchNumOfDoctor);
			result = stmt.executeQuery();
			int numOfDoctor = 0;
			if (result.next()) {
				numOfDoctor = result.getInt("numOfDoctor");
			}

			// Create DashboardInfoModel with customer specific info
			DashboardInfoModel dashboardModel = new DashboardInfoModel(numOfDoctor, numOfAppointment);
			return dashboardModel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
