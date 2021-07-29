<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drop Course</title>
</head>
<body>
<t:wrapper>
<div class="container">
	<h1>Course: ${oneGrade.course.name}</h1> <p>${oneGrade.course.semester }</p>
	<p>Teacher: ${oneGrade.course.teacher.name}</p>
	<p>Credits: ${oneGrade.course.credits}</p>
	<p>Capacity: ${oneGrade.course.capacity}</p>
	
	<a href="/student/drop/${oneGrade.id}"><button class="btn btn-secondary">Drop Class</button></a>
</div>
</t:wrapper>
</body>
</html>