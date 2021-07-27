<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>     
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Course Manager - Admin</title>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="/css/style.css">
	<script src="/js/script.js"></script>
</head>
<body>
<t:wrapper>
    <h3>
    	Course: 
	   	<c:out value="${course.name}"/>
	   	(<c:out value="${course.code}"/>)
    </h3>
    
    <p>Semester: <c:out value="${course.semester}"/></p>
    <p>Credits: <c:out value="${course.credits}"/></p>
    <p>Capacity: <c:out value="${course.capacity}"/></p>
    <p>Teacher: <c:out value="${course.teacher.name}"/></p>
    
    <div>
		<input onclick="editCourse(${course.id});" value="Edit" class="btn btn-primary my-button"/>
		<input onclick="deleteCourse(${course.id});" value="Delete" class="btn btn-primary my-button"/>
    </div>
</t:wrapper>
</body>
</html>