package com.groupproject.coursemanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.coursemanager.models.Course;

@Repository
public interface AdminRepository extends CrudRepository<Course, Long> {
    List<Course>findAll();
    
	@Query(value=""
			+ "SELECT DISTINCT semester "
			+ "FROM courses "
			+ "ORDER BY semester", nativeQuery=true)
	List<String> findAllSemesters();

	List<Course> findBySemester(String semester);

}
