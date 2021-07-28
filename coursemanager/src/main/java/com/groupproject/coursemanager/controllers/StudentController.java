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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.services.AdminService;
import com.groupproject.coursemanager.services.StudentService;
import com.groupproject.coursemanager.services.UserService;
import com.groupproject.coursemanager.validators.StudentValidator;

@Controller()
public class StudentController {
	@Autowired
	private StudentService studServe;
	@Autowired
	private AdminService adminServe;
	@Autowired
	private UserService uServe;
	@Autowired
	private StudentValidator studVal;
	
	@GetMapping("/student/home")
	public String home(Model viewModel, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User studentt = uServe.findUserById(userId);
		
		
		String lastSelectedSemester = (String) session.getAttribute("semester");
		// System.out.println("Last Selected");
		// System.out.println(lastSelectedSemester);
		
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
		
//		List<Course> users = studServe.findByUserSemester(lastSelectedSemester);
		List<Course> coursess = adminServe.findCoursesBySemester(lastSelectedSemester);
		viewModel.addAttribute("students", coursess);

		return setPage("/student/courseList.jsp", session);
	}

	@GetMapping("/student/semester/{semester}")
	public String filterBySemester(@PathVariable("semester") String semester,
								   HttpSession session) {
		session.setAttribute("semester", semester);
		return "redirect:/student/home";
	}
	@GetMapping("/student/course/{id}")
	public String studentHome(Model model, HttpSession session, @PathVariable("id")Long id) {
		model.addAttribute("oneCourse", this.adminServe.findCourse(id));
		return setPage("/student/studentCourse.jsp", session);
	}
	@GetMapping("/student/drop/course/{id}")
	public String dropCourse(Model model, HttpSession session, @PathVariable("id")Long id){
		model.addAttribute("oneCourse", this.adminServe.findCourse(id));
		return setPage("/student/courseDrop.jsp", session);
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
