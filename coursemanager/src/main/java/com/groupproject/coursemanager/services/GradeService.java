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
	public void addGrade(User student, Course course, Grade grade) {
		if (student == null && course == null) {
		Grade newGrade = new Grade();
		newGrade.setCourse(course);
		newGrade.setStudent(student);
		this.gRepo.save(newGrade);
		} else {
			this.gRepo.save(grade);
		}
	}
	public void registerClass(User student, Course course) {
		Grade newGrade = new Grade();
		newGrade.setCourse(course);
		newGrade.setStudent(student);
		this.gRepo.save(newGrade);
	}
	
	

	
	//edit grade
	public Grade editGrade(Grade grade) {
		return this.gRepo.save(grade);
	}
	public void dropGrade(Long id) {
		this.gRepo.deleteById(id); 
	}
}
