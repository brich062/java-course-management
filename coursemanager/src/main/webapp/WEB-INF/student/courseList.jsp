<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="/js/studentScript.js"></script>
</head>
<body>
	<div class="listToolbar">
		<div>
			Courses
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
		<table class="listWrapper">
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Code</th>
					<th>Semester</th>
					<th>Credits</th>
					<th>Teacher</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="studentss" items="${students}">
					<tr>
						<td><a href="/student/drop/course/${studentss.id}"><button>Drop</button></a></td>
						<td><a href="/student/course/${studentss.id}"><c:out value="${studentss.name}"/></a></td>
						<td><c:out value="${studentss.code}"/></td>
						<td><c:out value="${studentss.semester}"/></td>
						<td><c:out value="${studentss.credits}"/></td>
						<td><c:out value="${studentss.teacher.name}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/student/logout">Logout</a>
	</div>
</body>
</html>