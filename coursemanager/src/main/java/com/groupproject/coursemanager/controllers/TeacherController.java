package com.groupproject.coursemanager.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.Grade;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.services.AdminService;
import com.groupproject.coursemanager.services.GradeService;
import com.groupproject.coursemanager.services.UserService;

@Controller()
public class TeacherController {
	@Autowired UserService uServ;
	@Autowired AdminService aServ;
	@Autowired GradeService gServ;
	
	@GetMapping("/teacher/home")
	public String teacherHome(HttpSession session, Model viewModel) {
		//getting the user credentials
		Long userId = (Long) session.getAttribute("userId");
		//create user object for data view on the front end
		User u = uServ.findUserById(userId);
		//push data of course based on select semester parameter and will hold in session
		
		String lastSelectedSemester = (String) session.getAttribute("semester");
		List<String> semesters = aServ.findAllSemesters();
		viewModel.addAttribute("semesters", semesters);
		
		String filterBySemester = "";
		if (lastSelectedSemester == null) {
			filterBySemester = semesters.get(0);
		} else if (lastSelectedSemester.isBlank()) {
			filterBySemester = semesters.get(0);
		} else {
			filterBySemester = lastSelectedSemester;
			viewModel.addAttribute("lastSelectedSemester", lastSelectedSemester);
		}
		List<Course> courses = aServ.findCoursesBySemester(filterBySemester);
		viewModel.addAttribute("courses", courses);
		return setPage("/teacher/teacherHome.jsp", session);
	}
	
	@GetMapping("/teacher/semester/{semester}")
	public String filterBySemester(@PathVariable("semester") String semester,
								   HttpSession session) {
		session.setAttribute("semester", semester);
		return "redirect:/teacher/home";
	}
	
	@GetMapping("teacher/course/{id}")
	public String courseView(@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("userId");
		User u = uServ.findUserById(userId);
		//create the course object to pull grades for roster purposes
		//Will need to add Course Sevice
		Course viewCourse = this.aServ.findCourse(id);
		viewModel.addAttribute("course", viewCourse);
		viewModel.addAttribute("roster", viewCourse.getGrades());
		return  setPage("/teacher/courseView.jsp", session);
	}
	
	@GetMapping("teacher/course/{cId}/student/{sId}")
	public String studentView(@ModelAttribute("grade") Grade sGrade,@PathVariable("cId") Long cId, @PathVariable("sId") Long sId, HttpSession session,
			Model viewModel) {
		Long userId = (Long)session.getAttribute("userId");
		User u = uServ.findUserById(userId);
		Course viewCourse = this.aServ.findCourse(cId);
		viewModel.addAttribute("course", viewCourse);
		viewModel.addAttribute("student", this.uServ.findUserById(sId));
		return  setPage("/teacher/viewStudent.jsp", session);
	}
	
	
	
	//post mapping for posting a grade (based on modelAttribute?)
	@PostMapping("/teacher/addGrade/{cId}/{sId}")
	public String addGrade(@Valid @ModelAttribute("grade") Grade sGrade, BindingResult result, @PathVariable("cId") Long cId,
			@PathVariable("sId") Long sId, HttpSession session, Model viewModel) {
		//checking errors and pulling the page back up
		if(result.hasErrors()) {
			Long userId = (Long)session.getAttribute("userId");
			User u =  uServ.findUserById(userId);
			viewModel.addAttribute("student", this.uServ.findUserById(sId));
			return "viewStudent.jsp";
		}
		Course viewCourse = this.aServ.findCourse(cId);
		//User student = this.uServ.findUserById(sId);
		//adding the actual grade
		this.gServ.addGrade(sGrade);
		return "redirect:/teacher/course/" + viewCourse;
	}
	
	//set page function for allowing a redirect if drop down menu options are selected
	private String setPage(String page, HttpSession session) {
		session.setAttribute("currentpage", page);
		return page;
	}
}
