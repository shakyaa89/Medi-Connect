package com.mediconnect.util;

import java.time.LocalDate;
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
}
