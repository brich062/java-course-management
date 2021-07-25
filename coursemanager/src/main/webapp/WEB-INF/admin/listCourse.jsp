<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
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
	<script src="/js/script.js"></script>
</head>
<body>
	<div class="listToolbar">
		<div>
			Courses
			<input onclick="createCourse();" value="Create New Course" class="btn btn-primary my-button"/>
			Semester:
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
		</div>
		<table class="listWrapper">
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Code</th>
					<th>Semester</th>
					<th>Credits</th>
					<th>Capacity</th>
					<th>Teacher</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="course" items="${courses}">
					<tr>
						<td>
							<input onclick="editCourse(${course.id});" value="Edit" class="btn btn-primary my-button"/>
						</td>
						<td>
							<a href="/admin/courses/${course.id}">
								<c:out value="${course.name}"/>
							</a>
						</td>
						<td><c:out value="${course.code}"/></td>
						<td><c:out value="${course.semester}"/></td>
						<td><c:out value="${course.credits}"/></td>
						<td><c:out value="${course.capacity}"/></td>
						<td><c:out value="${course.teacher.name}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>