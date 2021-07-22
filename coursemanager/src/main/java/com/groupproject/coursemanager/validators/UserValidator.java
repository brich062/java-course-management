package com.groupproject.coursemanager.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.repositories.UserRepository;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserRepository uRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			errors.rejectValue("passwordConfirmation", "Match", "Passwords must match");
		}
		if(user.getPassword().equals("password")) {
			errors.rejectValue("password","Lame password", "C'mon make a better password");
		}
		if(uRepo.findByEmail(user.getEmail())!=null) {
			errors.rejectValue("email", "Unique", "Email already exists");
		}
		if(user.getRole().equals("null")) {
			errors.rejectValue("role","no roll", "must include role");
		}
	  }
}


