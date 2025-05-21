package com.mediconnect.model;

import java.time.LocalDate;

public class UserModel {
	private Integer user_id;

	// User personal details
	private String user_first_name;
	private String user_last_name;
	private String user_username;
	private String user_email;
	private String user_phonenumber;
	private String user_gender;
	private LocalDate user_dob;
	private String user_location;
	private String user_password;
	private String user_role;
	private String user_image;

	// Constructor with all fields including user_image
	public UserModel(Integer user_id, String user_first_name, String user_last_name, String user_username,
			String user_email, String user_phonenumber, String user_gender, LocalDate user_dob, String user_location,
			String user_password, String user_role, String user_image) {
		super();
		this.user_id = user_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_password = user_password;
		this.user_role = user_role;
		this.user_image = user_image;
	}

	// Constructor without user_id and user_image
	public UserModel(String user_first_name, String user_last_name, String user_username, String user_email,
			String user_phonenumber, String user_gender, LocalDate user_dob, String user_location, String user_password,
			String user_role) {
		super();
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_password = user_password;
		this.user_role = user_role;
	}

	// Constructor with user_image but without password and role
	public UserModel(Integer user_id, String user_first_name, String user_last_name, String user_username,
			String user_email, String user_phonenumber, String user_gender, String user_location, LocalDate user_dob,
			String user_image) {
		this.user_id = user_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_image = user_image;
	}

	// Constructor including user_role and user_image
	public UserModel(Integer user_id, String user_first_name, String user_last_name, String user_username,
			String user_email, String user_phonenumber, String user_gender, String user_location, String user_role,
			LocalDate user_dob, String user_image) {
		this.user_id = user_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_role = user_role;
		this.user_gender = user_gender;
		this.user_image = user_image;
	}

	// Constructor including password and role
	public UserModel(Integer user_id, String user_first_name, String user_last_name, String user_username,
			String user_email, String user_phonenumber, String user_gender, LocalDate user_dob, String user_location,
			String user_password, String user_role) {
		this.user_id = user_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_password = user_password;
		this.user_role = user_role;
	}

	// Constructor with password but no role or image
	public UserModel(Integer user_id, String user_first_name, String user_last_name, String user_username,
			String user_email, String user_phonenumber, String user_gender, LocalDate user_dob, String user_location,
			String user_password) {
		super();
		this.user_id = user_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_password = user_password;
	}

	// Constructor without password and role
	public UserModel(Integer user_id, String user_first_name, String user_last_name, String user_username,
			String user_email, String user_phonenumber, String user_gender, LocalDate user_dob, String user_location) {
		super();
		this.user_id = user_id;
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
	}

	// Constructor for login with username and password only
	public UserModel(String user_username, String user_password) {
		super();
		this.user_username = user_username;
		this.user_password = user_password;
	}

	// Constructor including image but no password or role
	public UserModel(String user_first_name, String user_last_name, String user_username, String user_email,
			String user_phonenumber, String user_gender, String user_location, String user_image, LocalDate user_dob) {
		super();
		this.user_first_name = user_first_name;
		this.user_last_name = user_last_name;
		this.user_username = user_username;
		this.user_email = user_email;
		this.user_phonenumber = user_phonenumber;
		this.user_gender = user_gender;
		this.user_dob = user_dob;
		this.user_location = user_location;
		this.user_image = user_image;
	}

	// Default no-argument constructor
	public UserModel() {
		super();
	}

	// Getters and Setters for all fields

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phonenumber() {
		return user_phonenumber;
	}

	public void setUser_phonenumber(String user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public LocalDate getUser_dob() {
		return user_dob;
	}

	public void setUser_dob(LocalDate user_dob) {
		this.user_dob = user_dob;
	}

	public String getUser_location() {
		return user_location;
	}

	public void setUser_location(String user_location) {
		this.user_location = user_location;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

}
