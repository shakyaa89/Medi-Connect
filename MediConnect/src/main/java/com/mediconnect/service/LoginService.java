package com.mediconnect.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.mediconnect.config.Dbconfig;
import com.mediconnect.model.UserModel;
import com.mediconnect.util.PasswordEncryptionUtil;

public class LoginService {
	
	private Connection DbConnection;
	
	// Initialize DB connection
	public LoginService() {
		try {
			DbConnection = Dbconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}
	
	// Verify if user credentials are valid
	public Boolean loginUser(UserModel userModel){
		
		if(DbConnection == null) {
			System.out.println("Database not connected!");
			return null;
		}
		
		// SQL query to fetch username and password for given username
		String fetchStmt = "SELECT user_username, user_password FROM users WHERE user_username = ?";
		
		try {
			PreparedStatement stmt = DbConnection.prepareStatement(fetchStmt);
			stmt.setString(1, userModel.getUser_username());
			
			ResultSet result = stmt.executeQuery();

			// If user found, validate password
			if (result.next()) {
				return validatePassword(result, userModel);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		// Return false if no user found or invalid
		return false;
	}
	
	// Compare given password with encrypted password from DB
	public Boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException{
		String dbUsername = result.getString("user_username");
		String dbPassword = result.getString("user_password");
		
		// Decrypt DB password and compare with input password
		return dbUsername.equals(userModel.getUser_username()) && PasswordEncryptionUtil.decrypt(dbPassword).equals(userModel.getUser_password());
	}
	
	// Fetch full UserModel object from DB by username
	public UserModel getUserObjectFromDatabase(String username) {
	    if (DbConnection == null) {
	        System.out.println("Database not connected!");
	        return null;
	    }

	    String fetchUserQuery = "SELECT * FROM users WHERE user_username = ?";
	    ResultSet results = null;

	    try {
	        PreparedStatement fetchUserStmt = DbConnection.prepareStatement(fetchUserQuery);
	        fetchUserStmt.setString(1, username);
	        results = fetchUserStmt.executeQuery();

	        // Map database record to UserModel object if user found
	        if (results.next()) {
	            int userId = results.getInt("user_id");
	            String name = results.getString("user_first_name");
	            String lastName = results.getString("user_last_name");
	            String usernameDb = results.getString("user_username");
	            String email = results.getString("user_email");
	            String phoneNum = results.getString("user_phonenumber");
	            LocalDate birthday = results.getDate("user_dob").toLocalDate();
	            String location = results.getString("user_location");
	            String userRole = results.getString("user_role");
	            String gender = results.getString("user_gender");
	            String image = results.getString("user_image");
	            String password = results.getString("user_password");

	            return new UserModel(userId, name, lastName, usernameDb, email, phoneNum, gender, birthday, location, password, userRole, image);
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error fetching user data from the database.");
	        return null;
	    }
	}
	
}
