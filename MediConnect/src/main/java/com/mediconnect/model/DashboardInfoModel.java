package com.mediconnect.model;

public class DashboardInfoModel {
	private int numOfDoctor;
	private int numOfStaff;
	private int numOfAppointment;
	private int numOfCustomer;
	private int numOfMaleCustomer;
	private int numOfFemaleCustomer;
	private int numOfMaleStaff;
	private int numOfFemaleStaff;

	public DashboardInfoModel(int numOfDoctor, int numOfStaff, int numOfAppointment, int numOfCustomer,
			int numOfMaleCustomer, int numOfFemaleCustomer, int numOfMaleStaff, int numOfFemaleStaff) {
		super();
		this.numOfDoctor = numOfDoctor;
		this.numOfStaff = numOfStaff;
		this.numOfAppointment = numOfAppointment;
		this.numOfCustomer = numOfCustomer;
		this.numOfMaleCustomer = numOfMaleCustomer;
		this.numOfFemaleCustomer = numOfFemaleCustomer;
		this.numOfMaleStaff = numOfMaleStaff;
		this.numOfFemaleStaff = numOfFemaleStaff;
	}

	public DashboardInfoModel(int numOfDoctor, int numOfAppointment) {
		super();
		this.numOfDoctor = numOfDoctor;
		this.numOfAppointment = numOfAppointment;
	}



	public int getNumOfMaleStaff() {
		return numOfMaleStaff;
	}

	public void setNumOfMaleStaff(int numOfMaleStaff) {
		this.numOfMaleStaff = numOfMaleStaff;
	}

	public int getNumOfFemaleStaff() {
		return numOfFemaleStaff;
	}

	public void setNumOfFemaleStaff(int numOfFemaleStaff) {
		this.numOfFemaleStaff = numOfFemaleStaff;
	}

	public int getNumOfDoctor() {
		return numOfDoctor;
	}

	public void setNumOfDoctor(int numOfDoctor) {
		this.numOfDoctor = numOfDoctor;
	}

	public int getNumOfStaff() {
		return numOfStaff;
	}

	public void setNumOfStaff(int numOfStaff) {
		this.numOfStaff = numOfStaff;
	}

	public int getNumOfAppointment() {
		return numOfAppointment;
	}

	public void setNumOfAppointment(int numOfAppointment) {
		this.numOfAppointment = numOfAppointment;
	}

	public int getNumOfCustomer() {
		return numOfCustomer;
	}

	public void setNumOfCustomer(int numOfCustomer) {
		this.numOfCustomer = numOfCustomer;
	}

	public int getNumOfMaleCustomer() {
		return numOfMaleCustomer;
	}

	public void setNumOfMaleCustomer(int numOfMaleCustomer) {
		this.numOfMaleCustomer = numOfMaleCustomer;
	}

	public int getNumOfFemaleCustomer() {
		return numOfFemaleCustomer;
	}

	public void setNumOfFemaleCustomer(int numOfFemaleCustomer) {
		this.numOfFemaleCustomer = numOfFemaleCustomer;
	}

}
