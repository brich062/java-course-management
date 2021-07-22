package com.groupproject.coursemanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.services.UserService;
import com.groupproject.coursemanager.validators.UserValidator;

@Controller()
public class MainController {
	@Autowired
	private UserValidator validator;

	@Autowired
	private UserService uService;
	
	@RequestMapping("/")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
	    return "loginRegistration.jsp";
	}    
	@PostMapping(value="/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
	    validator.validate(user, result);
		if(result.hasErrors()) {
	    	return "loginRegistration.jsp";
	    }
	    User u = uService.registerUser(user);
	    session.setAttribute("userId", u.getId());
	    return "redirect:/home";
	}
	
	@PostMapping(value="/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttr) {
	   boolean isAuthenticated = uService.authenticateUser(email, password);
	   if(isAuthenticated) {
		   User u = uService.findByEmail(email);
		   session.setAttribute("userId", u.getId());
		   return "redirect:/home";
	   } else {
    	   redirectAttr.addFlashAttribute("error", "Email or Password incorrect.");
    	   return "redirect:/";
       }
	}
}