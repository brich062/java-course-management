package com.groupproject.coursemanager.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.services.UserService;

@Controller()
public class TeacherController {
	@Autowired UserService uServ;
	@Autowired CourseService cServ;
	
	@GetMapping("/teacher/home")
	public String teacherHome(HttpSession session, Model viewModel) {
		//getting the user credentials
		Long userId = (Long) session.getAttribute("userId");
		//create user object for data view on the front end
		User u = uServ.findUserById(userId);
		//push data of courses via getTeaches for the List<Courses>
		viewModel.addAttribute("courses", u.getTeaches());
		return "teacherHome.jsp";
	}
	
	@GetMapping("teacher/course/{id}")
	public String courseView(@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		Long userId = (Long)session.getAttribute("userId");
		User u = uServ.findUserById(userId);
		//create the course object to pull grades for roster purposes
		Course viewCourse = this.cServ.getById("id");
		viewModel.addAttribute("roster", viewCourse.getGrades());
		return "courseView.jsp";
	}
	
	@GetMapping("teacher/course/{cId}/student/{sId}")
	public String studentView(@PathVariable("cId") Long cId, @PathVariable("sId") Long sId, HttpSession session,
			Model viewModel) {
		Long userId = (Long)session.getAttribute("userId");
		User u = uServ.findUserById(userId);
		Course viewCourse = this.cServ.getById("cId");
		viewModel.addAttribute("student", this.uServ.findUserById(sId));
		return "viewStudent.jsp";
	}
	
	//post mapping for posting a grade (based on modelAttribute?)
}
