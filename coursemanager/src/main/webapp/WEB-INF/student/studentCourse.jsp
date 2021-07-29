<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>  
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>ADD COURSE</title>
</head>
<body>
<t:wrapper>
<div class="container">
	<h1>Course: ${oneCourse.name}</h1> <p>${oneCourse.semester }</p>
	<p>Teacher: ${oneCourse.teacher.name}</p>
	<p>Credits: ${oneCourse.credits}</p>
	<p>Capacity: ${oneCourse.capacity}</p>
	
</div>
</body>
</t:wrapper>
</html>

