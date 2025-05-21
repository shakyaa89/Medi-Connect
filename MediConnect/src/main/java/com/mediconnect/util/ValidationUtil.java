package com.mediconnect.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class ValidationUtil {

	public boolean isValidPassword(String password, String retypePassword) {
      // Implement password validation logic
      return password.length() > 6 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")
              && password.matches(".*[A-Z].*");
  }
	
	public boolean isPasswordSame(String password, String retypePassword){
  	return password.equals(retypePassword);
  }
  
	public boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^98\\d{8}$");
    }
	
	public boolean isAgeAtLeast16(LocalDate dob) {
        if (dob == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        return Period.between(dob, today).getYears() >= 18;
    }
	
	public boolean isAppointmentValid(LocalDate appDate) {
        if (appDate == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate sixMonthsLater = today.plusMonths(6);

        return !appDate.isBefore(tomorrow) && !appDate.isAfter(sixMonthsLater);
    }
	
	public boolean isTimeValid(String startTime, String endTime, String enteredTime) {
	    if (startTime == null || endTime == null || enteredTime == null) {
	        return false;
	    }

	    try {
	    	LocalTime startTimeCompare = LocalTime.parse(startTime);
	    	LocalTime endTimeCompare = LocalTime.parse(endTime);
	    	LocalTime enteredTimeCompare = LocalTime.parse(enteredTime);
	    	
	    	return !enteredTimeCompare.isBefore(startTimeCompare) && !enteredTimeCompare.isAfter(endTimeCompare);
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }
	}
}
