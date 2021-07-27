package com.groupproject.coursemanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.repositories.AdminRepository;

@Service
public class AdminService {
	private final AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public List<String> findAllSemesters() {
    	return this.adminRepository.findAllSemesters();
    }

	public List<Course> findCoursesBySemester(String semester) {
    	return this.adminRepository.findBySemester(semester);
    }
	
	public Course findCourse(Long id) {
    	return this.adminRepository.findById(id).orElse(null);
    }

	public Course saveCourse(Course course) {
		return this.adminRepository.save(course);
	}

	public void deleteCourse(Course course) {
		this.adminRepository.delete(course);
	}
	
}
