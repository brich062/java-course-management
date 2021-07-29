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
    <h3>Edit Course</h3>
    
    <p class="formErrors"><form:errors path="course.*"/></p>
    
    <form:form method="POST" action="/admin/courses/${course.id}/edit" modelAttribute="course">
    	<table class="formWrapper">
	    	<tr>
	    		<td>
	    			<form:label path="name">Name:</form:label>
	    		</td>
	    		<td>
	    			<form:input path="name"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<form:label path="code">Code:</form:label>
	    		</td>
	    		<td>
	    			<form:input path="code"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<form:label path="semester">Semester:</form:label>
	    		</td>
	    		<td>
	    			<form:input path="semester"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<form:label path="credits">Credits:</form:label>
	    		</td>
	    		<td>
	    			<form:input path="credits"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<form:label path="capacity">Capacity:</form:label>
	    		</td>
	    		<td>
	    			<form:input path="capacity"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			<form:label path="teacher">Teacher:</form:label>
	    		</td>
	    		<td>
					<form:select path="teacher">
						<c:forEach var="teacher" items="${teachers}">
						   <form:option value="${teacher.id}" label="${teacher.name}"/>
						</c:forEach>
					</form:select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="2" class="formButtons">
	    			<input type="submit" value="Update" class="btn btn-primary my-button"/>
	    		</td>
	    	</tr>
    	</table>
    </form:form>
</t:wrapper>
</body>
</html>