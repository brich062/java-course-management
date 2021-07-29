<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>    
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/style.css">
    <title><c:out value="${user.name}" />'s Profile</title>
</head>
<body>
<t:wrapper>
	<div class="row gutters-sm">
		<div class="col-md-3 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="${user.profilePic}" alt="Admin" class="profileLarge">
                    <div class="mt-3">
                      <h4><c:out value="${user.name}" /></h4>
                     			<c:choose>
								<c:when test = "${sessionUser.id == user.id}">
								<hr>
									<form method="POST" action="/upload/${sessionUser.id}" enctype= "multipart/form-data">
									<div class="form-data"><input type="file" name="picture"></div>
									<button class="btn btn-primary">Update Profile Picture</button>
									</form>
								</c:when>
								</c:choose>
                    </div>
                  </div>
                  
                </div>
                
			</div>
			</div>
</t:wrapper>
</body>