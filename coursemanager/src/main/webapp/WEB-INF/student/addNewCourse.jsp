<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<t:wrapper>
	<div class="container">
			<form:form method="post" action="/student/add/course" modelAttribute="grade">
			<div class="form-group">
		       	<form:label path="course">Course: </form:label>
		       	<form:errors path="course"/>
		        <form:select path="course">
			        <c:forEach items="${coursess}" var="thisCor">
			        <p>hey</p>
			        	<option value="${thisCor.id}">${thisCor.name}</option>
					</c:forEach>
				</form:select>
	  		</div>
	  		<button type="submit" class="btn btn-outline-primary btn-block">Add Course</button>
	  	</form:form>
	</div>
</t:wrapper>
</body>
</html>