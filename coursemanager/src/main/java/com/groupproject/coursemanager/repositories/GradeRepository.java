package com.groupproject.coursemanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.Grade;
import com.groupproject.coursemanager.models.User;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
	List<Grade> findAll();
	public Grade findByStudentAndCourse(User student, Course course);
}
