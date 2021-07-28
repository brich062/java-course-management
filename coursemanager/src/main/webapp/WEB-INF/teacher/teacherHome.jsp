<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Home</title>
<script src="/js/teacherScript.js"></script>
</head>
<body>
<h1>My Courses</h1>
<a href="/logout">Log Out</a>
<h4>Semester:</h4>
			<select onchange="filterBySemester(event);">
				<c:forEach var="semester" items="${semesters}">
					<c:choose>
						<c:when test="${semester.equals(lastSelectedSemester)}">
						   <option value="${semester}" selected="selected">${semester}</option>
						</c:when>
						<c:otherwise>
						   <option value="${semester}">${semester}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
<table>
	<thead>
		<tr>
			<td>Name</td>
			<td>Code</td>
			<td>Semester</td>
			<td>Credits</td>
			<td>Capacity</td>
	</thead>
	<tbody>
		<c:forEach items="${courses}" var="cor">
			<tr>
				<td><a href="/teacher/course/${cor.id}">${cor.name}</a></td>
				<td>${cor.code}</td>
				<td>${cor.semester}</td>
				<td>${cor.credits}</td>
				<td>${cor.capacity}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>