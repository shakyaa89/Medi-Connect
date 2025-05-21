package com.mediconnect.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mediconnect.config.Dbconfig;


public class DeleteService {
	private Connection dbConnection;

	public DeleteService() {
		try {
			this.dbConnection = Dbconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database Connection Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public Boolean deleteDoctor(int doctorId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		PreparedStatement delete = null;
		
		String fetchAppointmentId = "SELECT appointment_id FROM doctor_user_appointment WHERE doctor_id = ?";
		String deleteAppointmentsDoctors = "DELETE FROM doctor_user_appointment WHERE doctor_id = ?";
		String deleteDoctorUser = "DELETE FROM doctor_user WHERE doctor_id = ?";
		String deleteAvailability = "DELETE FROM doctor_availability WHERE doctor_id = ?";
		String deleteDoctor = "DELETE FROM doctors WHERE doctor_id = ?";
		String deleteAppointment = "DELETE FROM appointment WHERE appointment_id = ?";
		
		try {
			dbConnection.setAutoCommit(false);
						
			PreparedStatement fetchAppointmentIdStmt = dbConnection.prepareStatement(fetchAppointmentId);
			fetchAppointmentIdStmt.setInt(1, doctorId);
			ResultSet appointmentResult = fetchAppointmentIdStmt.executeQuery();
			
			ArrayList<Integer> appointmentIds = new ArrayList<>();
			
			while (appointmentResult.next()) {
				appointmentIds.add(appointmentResult.getInt("appointment_id"));
			}
			
			delete = dbConnection.prepareStatement(deleteAppointmentsDoctors);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			delete = dbConnection.prepareStatement(deleteDoctorUser);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			delete = dbConnection.prepareStatement(deleteAvailability);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			delete = dbConnection.prepareStatement(deleteDoctor);
			delete.setInt(1, doctorId);
			delete.executeUpdate();
			
			delete = dbConnection.prepareStatement(deleteAppointment);
			for(Integer appointmentId : appointmentIds) {
				delete.setInt(1, appointmentId);
				delete.executeUpdate();
			}
			
			
			dbConnection.commit();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
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
			dbConnection.setAutoCommit(false);
			
			delete = dbConnection.prepareStatement(deleteAppointmentsDoctors);
			delete.setInt(1, appointmentId);
			delete.executeUpdate();
			
			delete = dbConnection.prepareStatement(deleteDoctorUser);
			delete.setInt(1, userId);
			delete.executeUpdate();
			
			delete = dbConnection.prepareStatement(deleteAppointment);
			delete.setInt(1, appointmentId);
			delete.executeUpdate();
			
			dbConnection.commit();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean deleteStaff(int staffId) {
		if (dbConnection == null) {
			System.err.println("Database not connected!");
			return null;
		}
		
		
		String deleteStaff = "DELETE FROM users WHERE user_id = ?";
		
		try {
			dbConnection.setAutoCommit(false);
			
			PreparedStatement delete = dbConnection.prepareStatement(deleteStaff);
			delete.setInt(1, staffId);
			delete.executeUpdate();
			
			dbConnection.commit();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
