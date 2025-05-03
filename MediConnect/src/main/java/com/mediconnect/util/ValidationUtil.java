package com.mediconnect.util;

public class ValidationUtil {

	public boolean isValidPassword(String password, String retypePassword) {
      // Implement password validation logic
      return password.length() > 6 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")
              && password.matches(".*[A-Z].*");
  }
	
	public boolean isPasswordSame(String password, String retypePassword){
  	return password.equals(retypePassword);
  }
  
}
