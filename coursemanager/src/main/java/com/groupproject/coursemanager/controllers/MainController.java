package com.groupproject.coursemanager.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
	
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
	@RequestMapping("/home")
	public String placeUser(HttpSession session, User user, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User u = uService.findUserById(userId);
		String role = u.getRole();
		String student = "student";
		String teacher = "teacher";
		String admin = "admin";
    	model.addAttribute("sessionUser", u);
		System.out.println(u.getRole());
		System.out.println(role);
		if(u.getRole().equals(student)) {
			return "redirect:/student/home";
		}
		if(u.getRole().equals(teacher)) {
			return "redirect:/teacher/home";
		}
		if(u.getRole().equals(admin)) {
			return "redirect:/admin/home";
		} else {
			return "redirect:/";
		}	
	}
	@GetMapping("/profile/{id}")
	public String viewProfile(@ModelAttribute("user") User user, Model model, @PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId"); 
		session.setAttribute("currentpage", "profile/" + id);
        if(userId == null) {
      	   return "redirect:/";
          }
    	User u = uService.findUserById(userId);
        model.addAttribute("sessionUser", u);
		User oneUser = this.uService.findUserById(id);
		model.addAttribute("user", oneUser);
		return "profile.jsp";
	}
	@PostMapping("/upload/{id}")
	public String uploadProfilePic(@RequestParam("picture") MultipartFile file, HttpSession session, RedirectAttributes redirectAttr, @PathVariable("id") Long id) {
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message", "Upload an image");
			return "redirect:/home";
		}
		try {
			User user = this.uService.findUserById((Long) session.getAttribute("userId"));
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			String image_url = "/images/" + file.getOriginalFilename();
			this.uService.uploadProfilePic(user, image_url);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/profile/"+ id;
	}
	@GetMapping("/lightmode")
	public String lightMode(HttpSession session) {
		String pagename = (String)session.getAttribute("currentpage");
		User user = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.uService.lightMode(user);
		return "redirect:/" + pagename;
	}
	@GetMapping("/darkmode")
	public String darkMode(HttpSession session) {
		String pagename = (String)session.getAttribute("currentpage");
		User user = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.uService.darkMode(user);
		return "redirect:/" + pagename;
	}	
}