package com.mediconnect.model;

import java.time.LocalDate;

public class AppointmentListModel {
	// Unique ID for the appointment
	int appointment_id;
	
	// ID of the user 
	int user_id;
	
	// ID of the doctor
	int doctor_id;
	
	// Patient's first name
	String user_first_name;
	
	// Patient's last name
	String user_last_name;
	
	// Doctor's first name
	String doctor_first_name;
	
	// Doctor's last name
	String doctor_last_name;
	
	// Time of the appointment
	String appointment_time;
	
	// Date of the appointment
	LocalDate appointment_date;

	// Constructor to initialize all fields
	public AppointmentListModel(int appointment_id, int user_id, int doctor_id, String user_first_name,
			String user_last_name, String doctor_first_name, String doctor_last_name, String appointment_time,
			LocalDate appointment_date) {
		super();
		this.appointment_id = appointment_id;
		this.user_id = user_id;
		this.doctor_id = doctor_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.doctor_first_name = doctor_first_name;
		this.doctor_last_name = doctor_last_name;
		this.appointment_time = appointment_time;
		this.appointment_date = appointment_date;
	}

	// Getter and setter for doctor_id
	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	// Getter and setter for appointment_id
	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}

	// Getter and setter for user_id
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	// Getter and setter for user_first_name
	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	// Getter and setter for user_last_name
	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	// Getter and setter for doctor_first_name
	public String getDoctor_first_name() {
		return doctor_first_name;
	}

	public void setDoctor_first_name(String doctor_first_name) {
		this.doctor_first_name = doctor_first_name;
	}

	// Getter and setter for doctor_last_name
	public String getDoctor_last_name() {
		return doctor_last_name;
	}

	public void setDoctor_last_name(String doctor_last_name) {
		this.doctor_last_name = doctor_last_name;
	}

	// Getter and setter for appointment_time
	public String getAppointment_time() {
		return appointment_time;
	}

	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}

	// Getter and setter for appointment_date
	public LocalDate getAppointment_date() {
		return appointment_date;
	}

	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}

}
