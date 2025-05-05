package com.mediconnect.model;

import java.time.LocalDate;

public class AppointmentModel {
	Integer appointment_id;
	LocalDate appointment_date; 
	String appointment_time;
	String status;
	
	public AppointmentModel(Integer appointment_id, LocalDate appointment_date, String appointment_time, String status) {
		super();
		this.appointment_id = appointment_id;
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
		this.status = status;
	}
	
	public Integer getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(Integer appointment_id) {
		this.appointment_id = appointment_id;
	}
	public LocalDate getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
