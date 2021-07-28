<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drop Course</title>
</head>
<body>
<div class="container">
	<h1>Course: ${oneCourse.name}</h1> <p>${oneCourse.semester }</p>
	<p>Teacher: ${oneCourse.teacher.name}</p>
	<p>Credits: ${oneCourse.credits}</p>
	<p>Capacity: ${oneCourse.capacity}</p>
	<a href="/student/drop/${oneCourse.id}"><button class="btn btn-secondary">Drop Class</button></a>
</div>
</body>
</html>