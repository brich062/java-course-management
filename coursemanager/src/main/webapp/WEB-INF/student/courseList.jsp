<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="/js/studentScript.js"></script>
</head>
<body>
<t:wrapper>

					<h2>Courses</h2>
		<c:choose>
								<c:when test = "${sessionUser.darkMode == true}">
								<table class="table table-dark">
								</c:when>
								<c:otherwise>
    							<table class="table table-light">
								</c:otherwise>
		</c:choose>
			<thead>
				<tr>
					<th></th>
					<th><p>Name</p></th>
					<th><p>Code</p></th>
					<th><p>Semester</p></th>
					<th><p>Credits</p></th>
					<th><p>Teacher</p></th>
					<th><p>Grade</p></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cors" items="${classes}">
		
			  			
							<tr>
								<td><a href="/student/drop/course/${cors.id}"><button>Drop</button></a></td>
								<td><a href="/student/course/${cors.course.id}"><c:out value="${cors.course.name}"/></a></td>
		 						<td><p><c:out value="${cors.course.code}"/></p></td>
								<td><p><c:out value="${cors.course.semester}"/></p></td>
								<td><p><c:out value="${cors.course.credits}"/></p></td>
								<td><p><c:out value="${cors.course.teacher.name}"/></p></td>
								<td><p><c:out value="${cors.grade}"/></td>
							</tr>


				</c:forEach>
			</tbody>
		</table>
		<hr>
		<div class="listToolbar">
		<div>
			<input onclick="addCourseStudent();" value="Register Course" class="btn btn-primary my-button"/>
			Semester:
			<select onchange="filterBySemesterStudent(event);">
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
</t:wrapper>
</body>
</html>