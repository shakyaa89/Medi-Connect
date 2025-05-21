package com.mediconnect.model;

import java.time.LocalDate;

public class AppointmentModel {
	// Appointment ID
	Integer appointment_id;
	
	// Date of the appointment
	LocalDate appointment_date;
	
	// Time of the appointment 
	String appointment_time;
	
	// Status of the appointment 
	String status;

	// Constructor with all fields
	public AppointmentModel(Integer appointment_id, LocalDate appointment_date, String appointment_time,
			String status) {
		super();
		this.appointment_id = appointment_id;
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
		this.status = status;
	}

	// Constructor with date and time only
	public AppointmentModel(LocalDate appointment_date, String appointment_time) {
		super();
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
	}

	// Getter and setter methods for appointment_id
	public Integer getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(Integer appointment_id) {
		this.appointment_id = appointment_id;
	}

	// Getter and setter methods for appointment_date
	public LocalDate getAppointment_date() {
		return appointment_date;
	}

	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}

	// Getter and setter methods for appointment_time
	public String getAppointment_time() {
		return appointment_time;
	}

	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}

	// Getter and setter methods for status
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
