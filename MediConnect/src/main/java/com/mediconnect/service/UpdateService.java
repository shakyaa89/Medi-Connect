package com.mediconnect.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.UserModel;

public class UpdateService {
	
private Connection dbConnection;
	
	public UpdateService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public Boolean updateUser(UserModel userModel, int userId) {
		if(dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		String updateQuery = "UPDATE users SET user_first_name = ?, user_last_name = ?, user_username = ?, user_email = ?, user_phonenumber = ?, user_gender = ?, user_dob = ?, user_location = ? WHERE user_id = ?;";
	
		try{
			PreparedStatement insertStmt = dbConnection.prepareStatement(updateQuery);
			insertStmt.setString(1, userModel.getUser_first_name());
			insertStmt.setString(2, userModel.getUser_last_name());
			insertStmt.setString(3, userModel.getUser_username());
			insertStmt.setString(4, userModel.getUser_email());
			insertStmt.setString(5, userModel.getUser_phonenumber());
			insertStmt.setString(6, userModel.getUser_gender());
			insertStmt.setDate(7, Date.valueOf(userModel.getUser_dob()));
			insertStmt.setString(8, userModel.getUser_location());
			insertStmt.setInt(9, userId);
			
			return insertStmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			System.err.println("SQL Error");
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer getUserId(String username) {
		if(dbConnection == null) {
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
			}else {
				return null;
			}
		}catch(Exception e) {
			System.out.print("Error Fetching User Id");
			return null;
		}
	}
	
	public Boolean updatePassword(String newPassword, String username) {
		if(dbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		String updateQuery = "UPDATE users SET user_password = ? WHERE user_username = ?";
		
		try{
			PreparedStatement insertStmt = dbConnection.prepareStatement(updateQuery);
			insertStmt.setString(1, newPassword);
			insertStmt.setString(2, username);
			
			return insertStmt.executeUpdate() > 0;
			
		}catch(SQLException e) {
			System.err.println("SQL Error");
			e.printStackTrace();
			return null;
		}
		
		
	}
}
