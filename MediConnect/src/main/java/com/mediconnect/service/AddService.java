package com.mediconnect.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.UserModel;

public class AddService {
	private Connection dbConnection;
	
	public AddService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public Boolean addDoctor(DoctorModel doctorModel, DoctorAvailabilityModel doctorAvailabilityModel) {
		
		if(dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		String doctorInsertQuery = "INSERT INTO doctors (doctor_id, doctor_first_name, doctor_last_name, doctor_email, doctor_phonenumber, doctor_address, doctor_gender, doctor_specialization, doctor_experience, doctor_image) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String doctorAvailabilityInsertQuery = "INSERT INTO doctor_availability (doctor_availability_id, doctor_id, start_time, end_time, doctor_available_day) VALUES (NULL, ?, ?, ?, ?)";
		String fetchDoctorId = "SELECT doctor_id FROM doctors WHERE doctor_phonenumber = ?";	
		
		try{
			PreparedStatement insertStmtDoctor = dbConnection.prepareStatement(doctorInsertQuery);
			insertStmtDoctor.setString(1, doctorModel.getDoctorFirstName());
			insertStmtDoctor.setString(2, doctorModel.getDoctorLastName());
			insertStmtDoctor.setString(3, doctorModel.getDoctorEmail());
			insertStmtDoctor.setString(4, doctorModel.getDoctorPhoneNumber());
			insertStmtDoctor.setString(5, doctorModel.getDoctorAddress());
			insertStmtDoctor.setString(6, doctorModel.getDoctorGender());
			insertStmtDoctor.setString(7, doctorModel.getDoctorSpecialization());
			insertStmtDoctor.setString(8, doctorModel.getDoctorExperience());
			insertStmtDoctor.setString(9, doctorModel.getDoctorImage());
			
			int isInserted = insertStmtDoctor.executeUpdate();
			
			if(isInserted > 0) {
				PreparedStatement fetchDoctorIdStmt = dbConnection.prepareStatement(fetchDoctorId);
				fetchDoctorIdStmt.setString(1, doctorModel.getDoctorPhoneNumber());

				ResultSet result = fetchDoctorIdStmt.executeQuery();
				if (result.next()) {
					int doctorId = result.getInt("doctor_id");
					
					PreparedStatement availabilityStmt = dbConnection.prepareStatement(doctorAvailabilityInsertQuery);
					availabilityStmt.setInt(1, doctorId);
					availabilityStmt.setString(2, doctorAvailabilityModel.getStart_time());
					availabilityStmt.setString(3, doctorAvailabilityModel.getEnd_time());
					availabilityStmt.setString(4, doctorAvailabilityModel.getDoctor_available_day());
					
					return availabilityStmt.executeUpdate() > 0;
				}else {
					System.out.println("Error Fetching Doctor_ID");
					return null;
				}
			}
			
			return null;
			
		}catch(SQLException e) {
			System.err.println("Error adding doctor!");
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean addStaff(UserModel UserModel) {
		if(dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		String insertQuery = "INSERT INTO users (user_id, user_first_name, user_last_name, user_username, user_email, user_phonenumber, user_gender, user_dob, user_location, user_password, user_role, user_image) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			PreparedStatement insertStmt = dbConnection.prepareStatement(insertQuery);
			insertStmt.setString(1, UserModel.getUser_first_name());
			insertStmt.setString(2, UserModel.getUser_last_name());
			insertStmt.setString(3, UserModel.getUser_username());
			insertStmt.setString(4, UserModel.getUser_email());
			insertStmt.setString(5, UserModel.getUser_phonenumber());
			insertStmt.setString(6, UserModel.getUser_gender());
			insertStmt.setDate(7, Date.valueOf(UserModel.getUser_dob()));
			insertStmt.setString(8, UserModel.getUser_location());
			insertStmt.setString(9, UserModel.getUser_password());
			insertStmt.setString(10, UserModel.getUser_role());
			insertStmt.setString(11, UserModel.getUser_image());
			
			return insertStmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			System.err.println("Error");
			e.printStackTrace();
			return null;
		}
	}
}
