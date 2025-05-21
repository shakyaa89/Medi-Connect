package com.mediconnect.model;

public class DoctorModel {
	private Integer doctor_id;
	
	// Doctor personal details
	private String doctorFirstName;
	private String doctorLastName;
	private String doctorEmail;
	private String doctorPhoneNumber;
	private String doctorAddress;
	private String doctorGender;
	private String doctorSpecialization;
	private String doctorExperience;
	private String doctorImage;
	
	// Constructor without doctor_id 
	public DoctorModel(String doctorFirstName, String doctorLastName, String doctorEmail,
			String doctorPhoneNumber, String doctorAddress, String doctorGender, String doctorSpecialization,
			String doctorExperience, String doctorImage) {
		super();
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorEmail = doctorEmail;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorAddress = doctorAddress;
		this.doctorGender = doctorGender;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.doctorImage = doctorImage;
	}
	
	// Constructor with doctor_id but without image
	public DoctorModel(Integer doctor_id, String doctorFirstName, String doctorLastName, String doctorEmail,
			String doctorPhoneNumber, String doctorAddress, String doctorGender, String doctorSpecialization,
			String doctorExperience) {
		this.doctor_id = doctor_id;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorEmail = doctorEmail;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorAddress = doctorAddress;
		this.doctorGender = doctorGender;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
	}
	
	// Constructor with all fields including doctor_id and image
	public DoctorModel(Integer doctor_id, String doctorFirstName, String doctorLastName, String doctorEmail,
			String doctorPhoneNumber, String doctorAddress, String doctorGender, String doctorSpecialization,
			String doctorExperience, String doctorImage) {
		super();
		this.doctor_id = doctor_id;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorEmail = doctorEmail;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorAddress = doctorAddress;
		this.doctorGender = doctorGender;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.doctorImage = doctorImage;
	}

	// Getter and setter for doctorImage
	public String getDoctorImage() {
		return doctorImage;
	}

	public void setDoctorImage(String doctorImage) {
		this.doctorImage = doctorImage;
	}

	// Getter and setter for doctor_id
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	// Getter and setter for doctorFirstName
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	
	// Getter and setter for doctorLastName
	public String getDoctorLastName() {
		return doctorLastName;
	}
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	
	// Getter and setter for doctorEmail
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	
	// Getter and setter for doctorPhoneNumber
	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}
	public void setDoctorPhoneNumber(String doctorPhoneNumber) {
		this.doctorPhoneNumber = doctorPhoneNumber;
	}
	
	// Getter and setter for doctorAddress
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	
	// Getter and setter for doctorGender
	public String getDoctorGender() {
		return doctorGender;
	}
	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}
	
	// Getter and setter for doctorSpecialization
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}
	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	
	// Getter and setter for doctorExperience
	public String getDoctorExperience() {
		return doctorExperience;
	}
	public void setDoctorExperience(String doctorExperience) {
		this.doctorExperience = doctorExperience;
	}
	
}
