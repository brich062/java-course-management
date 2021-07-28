package com.groupproject.coursemanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.coursemanager.models.Course;
import com.groupproject.coursemanager.models.User;

@Repository
public interface StudentRepository extends CrudRepository<Course, Long>{
	List<Course> findByOneStudent(User user);

}
