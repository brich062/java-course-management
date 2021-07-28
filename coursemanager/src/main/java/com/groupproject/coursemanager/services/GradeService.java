package com.groupproject.coursemanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.Grade;
import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.repositories.GradeRepository;

@Service
public class GradeService {
	@Autowired
	private GradeRepository gRepo;
	@Autowired
	private AdminService aServ;
	@Autowired
	private UserService uServ;
	//find all grades
	public List<Grade> findAllGrades(){
		return this.gRepo.findAll();
	}
	
	//find single grade
	public Grade findSingleGrade(Long id) {
		return this.gRepo.findById(id).orElse(null);
	}
	
	//add grade
	public Grade addGrade(Grade grade) {
		return this.gRepo.save(grade);
	}
	
	//edit grade
	public void editGrade(User student, Course course, String grade) {
		Grade edited = new Grade();
		edited.setCourse(course);
		edited.setStudent(student);
		edited.setGrade(grade);
		this.gRepo.save(edited);
	}
}
