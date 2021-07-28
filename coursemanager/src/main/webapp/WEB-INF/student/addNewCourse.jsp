<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
			<form:form method="post" action="/student/course/add" modelAttribute="course">
			<div class="form-group">
		       	<form:label path="name">Course: </form:label>
		        <form:select path="name">
			        <c:forEach items="${coursess}" var="thisCor">
			        	<option value="${thisCor.id}">${thisCor.name}</option>
					</c:forEach>
				</form:select>
	  		</div>
	  		<button type="submit" class="btn btn-outline-primary btn-block">Add Category</button>
	  	</form:form>
	</div>
</body>
</html>