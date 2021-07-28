package com.groupproject.coursemanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.repositories.AdminRepository;
import com.groupproject.coursemanager.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studRepo;
	@Autowired
	private AdminRepository adminRepo;
	
	
	public List<Course> findCourseList(User user){
		return this.studRepo.findByOneStudent(user);
	}
	public Course addCourseToSemester(Course course) {
		return this.adminRepo.save(course);
		
	}
//	public Course creditCount(User student) {
//		Course courses = this.studRepo.findByCredits(student);
//		if(courses.getCredits()<= 18) {
//			return null;
//		}
//		return this.studRepo.save(cour
//	}


	public List<Course> findByUserSemester(String lastSelectedSemester) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
