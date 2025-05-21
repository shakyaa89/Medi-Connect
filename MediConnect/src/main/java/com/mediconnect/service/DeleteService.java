package com.mediconnect.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediconnect.config.Dbconfig;

public class DeleteService {
	private Connection dbConnection;

	// Initialize DB connection
	public DeleteService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// Delete doctor and related data from multiple tables
	public Boolean deleteDoctor(int doctorId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		PreparedStatement delete = null;
		
		// Queries to fetch appointments and delete related records
		String fetchAppointmentId = "SELECT appointment_id FROM doctor_user_appointment WHERE doctor_id = ?";
		String deleteAppointmentsDoctors = "DELETE FROM doctor_user_appointment WHERE doctor_id = ?";
		String deleteDoctorUser = "DELETE FROM doctor_user WHERE doctor_id = ?";
		String deleteAvailability = "DELETE FROM doctor_availability WHERE doctor_id = ?";
		String deleteDoctor = "DELETE FROM doctors WHERE doctor_id = ?";
		String deleteAppointment = "DELETE FROM appointment WHERE appointment_id = ?";
		
		try {
			dbConnection.setAutoCommit(false);  // Start transaction
						
			PreparedStatement fetchAppointmentIdStmt = dbConnection.prepareStatement(fetchAppointmentId);
			fetchAppointmentIdStmt.setInt(1, doctorId);
			ResultSet appointmentResult = fetchAppointmentIdStmt.executeQuery();
			
			// Collect all appointment IDs related to doctor
			ArrayList<Integer> appointmentIds = new ArrayList<>();
			while (appointmentResult.next()) {
				appointmentIds.add(appointmentResult.getInt("appointment_id"));
			}
			
			// Delete entries from doctor_user_appointment for this doctor
			delete = dbConnection.prepareStatement(deleteAppointmentsDoctors);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			// Delete entries from doctor_user for this doctor
			delete = dbConnection.prepareStatement(deleteDoctorUser);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			// Delete doctor availability entries
			delete = dbConnection.prepareStatement(deleteAvailability);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			// Delete doctor record itself
			delete = dbConnection.prepareStatement(deleteDoctor);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			// Delete all appointments associated with the doctor
			delete = dbConnection.prepareStatement(deleteAppointment);
			for(Integer appointmentId : appointmentIds) {
				delete.setInt(1, appointmentId);
				delete.executeUpdate();
			}
			
			dbConnection.commit();  // Commit transaction
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Delete appointment and related user-doctor mappings
	public Boolean deleteAppointment(int appointmentId, int userId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		PreparedStatement delete = null;
		
		String deleteAppointmentsDoctors = "DELETE FROM doctor_user_appointment WHERE appointment_id = ?";
		String deleteDoctorUser = "DELETE FROM doctor_user WHERE user_id = ?";
		String deleteAppointment = "DELETE FROM appointment WHERE appointment_id = ?";
		
		try {
			dbConnection.setAutoCommit(false);  // Start transaction
			
			// Delete from doctor_user_appointment for this appointment
			delete = dbConnection.prepareStatement(deleteAppointmentsDoctors);
			delete.setInt(1, appointmentId);
			delete.executeUpdate();
			
			// Delete from doctor_user for this user
			delete = dbConnection.prepareStatement(deleteDoctorUser);
			delete.setInt(1, userId);
			delete.executeUpdate();
			
			// Delete the appointment itself
			delete = dbConnection.prepareStatement(deleteAppointment);
			delete.setInt(1, appointmentId);
			delete.executeUpdate();
			
			dbConnection.commit();  // Commit transaction
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Delete staff/user record from users table
	public Boolean deleteStaff(int staffId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		String deleteStaff = "DELETE FROM users WHERE user_id = ?";
		
		try {
			dbConnection.setAutoCommit(false);  // Start transaction
			
			PreparedStatement delete = dbConnection.prepareStatement(deleteStaff);
			delete.setInt(1, staffId);
			delete.executeUpdate();
			
			dbConnection.commit();  // Commit transaction
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
