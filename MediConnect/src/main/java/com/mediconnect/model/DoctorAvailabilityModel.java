package com.mediconnect.model;

public class DoctorAvailabilityModel {
	private Integer doctor_availability_id;
	// ID of the doctor this availability belongs to
	private Integer doctor_id;
	// Start time of doctor 
	private String start_time;
	// End time of doctor 
	private String end_time;
	// Day of the week doctor is available 
	private String doctor_available_day;
	
	// Constructor without IDs
	public DoctorAvailabilityModel(String start_time, String end_time, String doctor_available_day) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.doctor_available_day = doctor_available_day;
	}

	// Constructor with all fields including IDs
	public DoctorAvailabilityModel(Integer doctor_availability_id, Integer doctor_id, String start_time,
			String end_time, String doctor_available_day) {
		super();
		this.doctor_availability_id = doctor_availability_id;
		this.doctor_id = doctor_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.doctor_available_day = doctor_available_day;
	}
	
	// Getter and setter for doctor_availability_id
	public Integer getDoctor_availability_id() {
		return doctor_availability_id;
	}
	public void setDoctor_availability_id(Integer doctor_availability_id) {
		this.doctor_availability_id = doctor_availability_id;
	}
	
	// Getter and setter for doctor_id
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	// Getter and setter for start_time
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	// Getter and setter for end_time
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	// Getter and setter for doctor_available_day
	public String getDoctor_available_day() {
		return doctor_available_day;
	}
	public void setDoctor_available_day(String doctor_available_day) {
		this.doctor_available_day = doctor_available_day;
	}
}
