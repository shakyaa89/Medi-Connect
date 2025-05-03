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
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.UserModel;


public class DashboardService {
	
	private Connection DbConnection;
	
	public DashboardService() {
		try {
			DbConnection = Dbconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}
	
	public List<UserModel> getUserList(){
		if(DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		List<UserModel> userList = new ArrayList<UserModel>();
		
		String fetchDoctorQuery = "SELECT * FROM users WHERE user_role = 'Customer'";
		
		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			
			ResultSet results = fetchStmt.executeQuery();
			
			while(results.next()) {
				Integer userId = results.getInt("user_id");
				String userFirstName = results.getString("user_first_name");
				String userLastName = results.getString("user_last_name");
				String userUsername = results.getString("user_username");
				String userEmail = results.getString("user_email");
				String userPhoneNumber = results.getString("user_phonenumber");
				String userGender = results.getString("user_gender");
				LocalDate userDOB = LocalDate.parse(results.getString("user_dob"));
				String userAddress = results.getString("user_location");
				
				
				UserModel userObj = new UserModel(userId, userFirstName, userLastName, userUsername, userEmail, userPhoneNumber, userGender, userDOB, userAddress);
				
				userList.add(userObj);
			}

			return userList;
			
		}catch(SQLException e) {
			System.out.println("Error creating user list!");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<DoctorModel> getDoctorList(){
		if(DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
		
		String fetchDoctorQuery = "SELECT * FROM doctors";
		
		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			
			ResultSet results = fetchStmt.executeQuery();
			
			while(results.next()) {
				Integer doctorId = results.getInt("doctor_id");
				String doctorFirstName = results.getString("doctor_first_name");
				String doctorLastName = results.getString("doctor_last_name");
				String doctorEmail = results.getString("doctor_email");
				String doctorPhoneNumber = results.getString("doctor_phonenumber");
				String doctorAddress = results.getString("doctor_address");
				String doctorGender = results.getString("doctor_gender");
				String doctorSpecialization = results.getString("doctor_specialization");
				String doctorExperience = results.getString("doctor_experience");
				DoctorModel doctorObj = new DoctorModel(doctorId, doctorFirstName, doctorLastName, doctorEmail, doctorPhoneNumber, doctorAddress, doctorGender, doctorSpecialization, doctorExperience);
				
				doctorList.add(doctorObj);
			}

			return doctorList;
			
		}catch(SQLException e) {
			System.out.println("Error creating doctor list!");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<UserModel> getStaffList(){
		if(DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		List<UserModel> userList = new ArrayList<UserModel>();
		
		String fetchDoctorQuery = "SELECT * FROM users WHERE user_role = 'Staff'";
		
		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			
			ResultSet results = fetchStmt.executeQuery();
			
			while(results.next()) {
				Integer staffId = results.getInt("user_id");
				String staffFirstName = results.getString("user_first_name");
				String staffLastName = results.getString("user_last_name");
				String staffUsername = results.getString("user_username");
				String staffEmail = results.getString("user_email");
				String staffPhoneNumber = results.getString("user_phonenumber");
				String staffGender = results.getString("user_gender");
				LocalDate staffDOB = LocalDate.parse(results.getString("user_dob"));
				String staffAddress = results.getString("user_location");
				
				
				UserModel userObj = new UserModel(staffId, staffFirstName, staffLastName, staffUsername, staffEmail, staffPhoneNumber, staffGender, staffDOB, staffAddress);
				
				userList.add(userObj);
			}

			return userList;
			
		}catch(SQLException e) {
			System.out.println("Error creating user list!");
			e.printStackTrace();
			return null;
		}
	}
}