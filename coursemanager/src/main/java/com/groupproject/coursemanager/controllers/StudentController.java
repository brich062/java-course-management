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

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.Grade;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.services.AdminService;
import com.groupproject.coursemanager.services.GradeService;
import com.groupproject.coursemanager.services.StudentService;
import com.groupproject.coursemanager.services.UserService;

@Controller()
public class StudentController {
	@Autowired
	private StudentService studServe;
	@Autowired
	private AdminService adminServe;
	@Autowired
	private UserService uServe;
	@Autowired
	private GradeService gServe;

	
	@GetMapping("/student/home")
	public String home(Model viewModel, HttpSession session, Model model) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";		
		}else {
			
		String lastSelectedSemester = (String) session.getAttribute("semester");
		
		Long userId = (Long)session.getAttribute("userId");
		User u = uServe.findUserById(userId);
		session.setAttribute("currentpage", "student/home");
		model.addAttribute("sessionUser", u);
		viewModel.addAttribute("classes", u.getAttends());
		List<String> semesters = adminServe.findAllSemesters();
		viewModel.addAttribute("semesters", semesters);
		
		String filterBySemester = "";
		if (lastSelectedSemester == null) {
			if (semesters.size() > 0) {
				filterBySemester = semesters.get(0);
			}
		} else if (lastSelectedSemester.isEmpty()) {
			if (semesters.size() > 0) {
				filterBySemester = semesters.get(0);
			}
		} else {
			filterBySemester = lastSelectedSemester;
			viewModel.addAttribute("lastSelectedSemester", lastSelectedSemester);
		}
		List <Course> coursese = this.adminServe.findCoursesBySemester(filterBySemester);
		viewModel.addAttribute("courses", coursese);
		
		return "/student/courseList.jsp";
	
		}
	}

	@GetMapping("/student/semester/{semester}")
	public String filterBySemester(@PathVariable("semester") String semester,
								   HttpSession session) {
		session.setAttribute("semester", semester);
		return "redirect:/student/home";
	}
	@GetMapping("/student/add/course")
	public String newCourse(@ModelAttribute("grade")Grade grade, HttpSession session, Model model) {
		Long userId = (Long)session.getAttribute("userId");
		User u = uServe.findUserById(userId);
		session.setAttribute("currentpage", "student/add/course");
		model.addAttribute("sessionUser", u);
		String lastSelectedSemester = (String) session.getAttribute("semester");
		List <Course> theseCor = this.studServe.findAll();
		
		model.addAttribute("coursess", theseCor);
		
		return "/student/addNewCourse.jsp";
	}
	@PostMapping("/student/add/course")
	public String addThisCourse(@Valid @ModelAttribute("grade")Grade grade, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "/student/addNewCourse.jsp";
		}else {
			User thisUser = this.uServe.findUserById((Long)session.getAttribute("userId"));
			Long newLong = (Long)session.getAttribute("userId");
			this.gServe.registerClass(thisUser, grade.getCourse());
			return ("redirect:/student/home");
		
		}
	}
	@GetMapping("/student/course/{id}")
	public String studentHome(Model model, HttpSession session, @PathVariable("id")Long id) {
		model.addAttribute("oneCourse", this.adminServe.findCourse(id));
		Long userId = (Long)session.getAttribute("userId");
		User u = uServe.findUserById(userId);
		session.setAttribute("currentpage", "student/course/" + id);
		model.addAttribute("sessionUser", u);
		return "student/studentCourse.jsp";
	}
	@GetMapping("/student/drop/course/{id}")
	public String dropCourse(Model model, HttpSession session, @PathVariable("id")Long id){
		Long userId = (Long)session.getAttribute("userId");
		User u = uServe.findUserById(userId);
		session.setAttribute("currentpage", "student/drop/course/"+id);
		model.addAttribute("sessionUser", u);
		model.addAttribute("oneCourse", this.adminServe.findCourse(id));
		model.addAttribute("oneGrade", this.gServe.findSingleGrade(id));
		return "/student/courseDrop.jsp";
	}
	@GetMapping("/student/drop/{id}")
	public String deleteGrade(Model model, HttpSession session, @PathVariable("id")Long id) {
		this.gServe.dropGrade(id);
		return "redirect:/student/home";
	}
	private String setPage(String page, HttpSession session) {
		session.setAttribute("currentpage", page);
		return page;
	}
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
