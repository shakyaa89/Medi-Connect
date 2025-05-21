package com.mediconnect.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.DoctorModel;

public class SearchService {

	private Connection dbConnection;

	// Initialize database connection
	public SearchService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// Search doctors by name or specialization with a keyword
	public List<DoctorModel> searchDoctor(String search){
		if (dbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		// SQL query to search doctors by concatenated name or specialization
		String searchQuery = "SELECT * FROM doctors WHERE CONCAT(doctor_first_name, ' ', doctor_last_name) LIKE ? OR doctor_first_name LIKE ? OR doctor_last_name LIKE ? OR doctor_specialization LIKE ?";
		
		List<DoctorModel> searchedDoctor = new ArrayList<DoctorModel>();
		
		try {
			PreparedStatement searchStmt = dbConnection.prepareStatement(searchQuery);
			String searchKeyword = "%" + search + "%";
			
			// Set the search keyword for each LIKE parameter
			searchStmt.setString(1, searchKeyword);
			searchStmt.setString(2, searchKeyword);
			searchStmt.setString(3, searchKeyword);
			searchStmt.setString(4, searchKeyword);
			
			ResultSet result = searchStmt.executeQuery();
			
			// Iterate over results and create DoctorModel objects
			while(result.next()) {
				Integer doctorId = result.getInt("doctor_id");
				String doctorFirstName = result.getString("doctor_first_name");
				String doctorLastName = result.getString("doctor_last_name");
				String doctorEmail = result.getString("doctor_email");
				String doctorPhoneNumber = result.getString("doctor_phonenumber");
				String doctorAddress = result.getString("doctor_address");
				String doctorGender = result.getString("doctor_gender");
				String doctorSpecialization = result.getString("doctor_specialization");
				String doctorExperience = result.getString("doctor_experience");
				String doctorImage = result.getString("doctor_image");
				
				DoctorModel doctorObj = new DoctorModel(doctorId, doctorFirstName, doctorLastName, doctorEmail, doctorPhoneNumber, doctorAddress, doctorGender, doctorSpecialization, doctorExperience, doctorImage);
				
				// Add doctor to the result list
				searchedDoctor.add(doctorObj);
			}
			
			return searchedDoctor;
			
		}catch(Exception e) {
			// Return null on exception
			return null;
		}
	}
	
}
