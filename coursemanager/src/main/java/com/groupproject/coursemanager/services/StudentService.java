package com.groupproject.coursemanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.Grade;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.repositories.AdminRepository;
import com.groupproject.coursemanager.repositories.GradeRepository;
import com.groupproject.coursemanager.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private GradeRepository gRepo;
	
	

	public List<Grade> findStudentGrade(User user){
		return this.gRepo.findByStudent(user);
	}
	public List<Course> findAll(){
		return this.studRepo.findAll();
	}
}
