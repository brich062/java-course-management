package com.groupproject.coursemanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.services.AdminService;
import com.groupproject.coursemanager.services.UserService;
@Controller()
public class AdminController {
	private final AdminService adminService;
	private final UserService userService;

	public AdminController(AdminService adminService, UserService userService) {
		this.adminService = adminService;
		this.userService = userService;
	}

	@GetMapping("/admin/home")
	public String home(Model viewModel, HttpSession session, Model model) {
		String lastSelectedSemester = (String) session.getAttribute("semester");
		// System.out.println("Last Selected");
		// System.out.println(lastSelectedSemester);
		
		List<String> semesters = adminService.findAllSemesters();
		viewModel.addAttribute("semesters", semesters);
		
		String filterBySemester = "";
		if (lastSelectedSemester == null) {
			filterBySemester = semesters.get(0);
		} else if (lastSelectedSemester == "") {
			filterBySemester = semesters.get(0);
		} else {
			filterBySemester = lastSelectedSemester;
			viewModel.addAttribute("lastSelectedSemester", lastSelectedSemester);
		}
		
		List<Course> courses = adminService.findCoursesBySemester(filterBySemester);
		viewModel.addAttribute("courses", courses);
		session.setAttribute("currentpage", "admin/home");
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("sessionUser", u);
		return "/admin/listCourse.jsp";
	}

	@GetMapping("/admin/semester/{semester}")
	public String filterBySemester(@PathVariable("semester") String semester,
								   HttpSession session) {
		session.setAttribute("semester", semester);
		return "redirect:/admin/home";
	}

	@GetMapping("/admin/courses/add")
	public String addCourse(@ModelAttribute("course") Course course, 
							HttpSession session, Model viewModel, Model model) {
		List<User> teachers = userService.findByRole("teacher");
		viewModel.addAttribute("teachers", teachers);
		session.setAttribute("currentpage", "admin/courses/add");
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("sessionUser", u);		
		return "/admin/addCourse.jsp";
	}
	
	@PostMapping("/admin/courses/add")
	public String add(@Valid @ModelAttribute("course") Course course, 
					  BindingResult result, Model viewModel, HttpSession session) {
		// if (!authenticate(viewModel, session)) {
		// 	return "redirect:/";
		// }

		if (result.hasErrors()) {
			List<User> teachers = userService.findByRole("teacher");
			viewModel.addAttribute("teachers", teachers);

			return setPage("/admin/addCourse.jsp", session);
		} else {
			adminService.saveCourse(course);
			return "redirect:/admin/home";
		}
	}	

	@GetMapping("/admin/courses/{id}/edit")
	public String editCourse(@PathVariable("id") Long id, 
							 @ModelAttribute("course") Course course, 
							 HttpSession session, Model viewModel, Model model) {
		course = adminService.findCourse(id);
		viewModel.addAttribute("course", course);
		session.setAttribute("currentpage", "admin/courses/"+ id +"/edit");
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
		model.addAttribute("sessionUser", u);
		List<User> teachers = userService.findByRole("teacher");
		viewModel.addAttribute("teachers", teachers);

		return "/admin/editCourse.jsp";
	}

	@PostMapping("/admin/courses/{id}/edit")
	public String edit(@Valid @ModelAttribute("course") Course course, 
					   BindingResult result, 
					   Model viewModel, HttpSession session) {
		// if (!authenticate(viewModel, session)) {
		//	return "redirect:/";
		// }

		if (result.hasErrors()) {
			List<User> teachers = userService.findByRole("teacher");
			viewModel.addAttribute("teachers", teachers);

			return setPage("/admin/editCourse.jsp", session);
		} else {
			adminService.saveCourse(course);
			return "redirect:/admin/home";
		}
	}

	@GetMapping("/admin/courses/{id}")
	public String viewCourse(@PathVariable("id") Long id, 
							 HttpSession session, Model viewModel, Model model) {
		session.setAttribute("currentpage", "admin/courses/"+ id);
		Course course = adminService.findCourse(id);
		viewModel.addAttribute("course", course);
		Long userId = (Long) session.getAttribute("userId");
		User u = userService.findUserById(userId);
    	model.addAttribute("sessionUser", u);
		return "/admin/viewCourse.jsp";
	}
	
	@GetMapping("/admin/courses/{id}/delete")
	public String delete(@PathVariable("id") Long id, 
						 Model viewModel, HttpSession session) {
		// if (!authenticate(model, session)) {
		//	return "redirect:/";
		// }

		Course course = adminService.findCourse(id);
		// studentService.deleteAllGradesForCourse(id);
		adminService.deleteCourse(course);

		return "redirect:/admin/home";
	}
	
	private String setPage(String page, HttpSession session) {
		session.setAttribute("currentpage", page);
		return page;
	}
	private Long setUser(User user, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		session.setAttribute("sessionUser", userId);
		return userId;
	}
}
