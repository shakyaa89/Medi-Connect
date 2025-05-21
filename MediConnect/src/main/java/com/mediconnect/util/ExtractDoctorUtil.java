package com.mediconnect.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.DoctorModel;

public class ExtractDoctorUtil {
	
	private Connection DbConnection;

	public ExtractDoctorUtil() {
		try {
			// Initialize database connection
			DbConnection = Dbconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}
	
	// Retrieves list of all doctors from database
	public List<DoctorModel> getDoctorList(){
		if(DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
		
		// SQL query to fetch all doctors
		String fetchDoctorQuery = "SELECT * FROM doctors";
		
		try {
			PreparedStatement fetchStmt = DbConnection.prepareStatement(fetchDoctorQuery);
			
			ResultSet results = fetchStmt.executeQuery();
			
			// Iterate through result set and create DoctorModel objects
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
}
