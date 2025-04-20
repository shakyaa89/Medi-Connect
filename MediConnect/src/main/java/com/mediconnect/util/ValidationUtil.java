package com.mediconnect.util;

public class ValidationUtil {
	
	public boolean isValidName(String name) {
      // Implement name validation logic
      return !name.matches(".*\\d.*") && !name.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
  }
  
	public boolean isValidUsername(String username) {
      // Implement username validation logic
      return username.length() > 6 && !username.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
  }
	public boolean isValidPhoneNumber(String phoneNumber) {
      // Implement phone number validation logic
      return phoneNumber.startsWith("+") && phoneNumber.length() == 14;
  }

	public boolean isValidPassword(String password, String retypePassword) {
      // Implement password validation logic
      return password.length() > 6 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")
              && password.matches(".*[A-Z].*");
  }
	
	public boolean isPasswordSame(String password, String retypePassword){
  	return password.equals(retypePassword);
  }
  
	public boolean isValidEmail(String email) {
  	return email.matches(".*@.*[.].*") || email.matches(".*[.].*@.*");
  }
  
}
