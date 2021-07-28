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
<h3>Course: ${course.name}</h3>
<p>Teacher: ${course.teacher}</p>
<p>Credits: ${course.credits}</p>
<p>Capacity: ${course.capacity}</p>
<h3>Student Roster:</h3>
<c:forEach items="${roster}" var="roster">
	<a href="teacher/course/${course.id}/student/${course.grades.student.id}">Grade</a>
	<p>${roster.student.firstName} ${roster.student.lastName}</p>
</c:forEach>
</body>
</html>