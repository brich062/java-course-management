<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Course</title>
</head>
<body>
<a href="/logout">Log Out</a>
<a href="/teacher/home">Home</a>
<h3>Course: ${course.name}</h3>
<p>Teacher: ${course.teacher.name}</p>
<p>Credits: ${course.credits}</p>
<p>Capacity: ${course.capacity}</p>
<h3>Student Roster:</h3>
<c:forEach items="${roster}" var="roster">
	<a href="/teacher/course/${course.id}/student/${roster.student.id}">Grade</a>
	<p>${roster.student.name}</p>
</c:forEach>
</body>
</html>