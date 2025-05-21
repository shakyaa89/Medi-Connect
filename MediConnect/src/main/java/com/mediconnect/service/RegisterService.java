package com.mediconnect.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.UserModel;

public class RegisterService {
	
	private Connection dbConnection;
	
	// Initialize database connection
	public RegisterService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// Add a new user to the database
	public Boolean addUser(UserModel UserModel) {
		if(dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		// SQL insert query to add user record
		String insertQuery = "INSERT INTO users (user_id, user_first_name, user_last_name, user_username, user_email, user_phonenumber, user_gender, user_dob, user_location, user_password, user_role, user_image) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			PreparedStatement insertStmt = dbConnection.prepareStatement(insertQuery);
			
			// Set parameters from UserModel
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
			
			// Execute insert and return true if successful
			return insertStmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			System.err.println("Error");
			e.printStackTrace();
			return null;
		}
	}
}
