<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grade Student</title>
</head>
<body>
<h3>Course: ${course.name}</h3>
<p>Teacher: ${course.teacher}</p>
<p>Credits: ${course.credits}</p>
<p>Capacity: ${course.capacity}</p>
<h3>Student: ${student.firstName} ${student.lastName}</h3>
<form:form method="post" action="/teacher/addGrade/${course.id}/${student.id}" modelAttribute="grade">
	<form:label path="grade">Grade</form:label>
	<form:errors path="grade"/>
	<form:input value="${course.student.attends.grade}" path="grade"/>
	<button>Save</button>
</form:form>
</body>
</html>