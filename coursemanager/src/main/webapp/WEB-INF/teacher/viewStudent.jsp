<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grade Student</title>
</head>
<body>
<t:wrapper>
<a href="/logout">Log Out</a>
<a href="/teacher/home">Home</a>
<h3>Course: ${course.name}</h3>
<p>Teacher: ${course.teacher.name}</p>
<p>Credits: ${course.credits}</p>
<p>Capacity: ${course.capacity}</p>
<h3>Student: ${student.name}</h3>
<form:form method="post" action="/teacher/addGrade/${course.id}/${student.id}" modelAttribute="value">
	<form:label path="grade">Grade</form:label>
	<form:errors path="grade"/>
	<form:input path="grade" type="text"/>
	<button>Save</button>
</form:form>
</t:wrapper>
</body>
</html>