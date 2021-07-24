<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="/css/style.css">
    <title>Registration Page</title>
</head>
<body>
<h1>LOGO coming soon</h1>

<div class="l-and-r-side">
	<h1 class="card-title mt-3 text-center loginH1">Login</h1>
    <p class="white"><c:out value="${error}"/></p>
    <form method="post" action="/login">

            <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Email Address" type="email">
            </div> <!-- form-group// -->
               <div class="form-group input-group">
    	<div class="input-group-prepend">
    	</div>
  
    	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
        <input name="password" class="form-control" placeholder="Password" type="password">
    </div> <!-- form-group// -->   
    

    </div> 
 <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">Login</button>
    </div> <!-- form-group// -->      
        
    </form>
	</div>
</div>
    
    <div class="l-and-r-side">
	<h1 class="card-title mt-3 text-center loginH1" class="loginH1">Register</h1>
    
    <p class="white"><form:errors path="user.*"/></p>
    
    
    
    
    <form:form method="POST" action="/registration" modelAttribute="user">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="name" class="form-control" placeholder="Name" type="text">
    </div> <!-- form-group// -->
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Email Address" type="email">
            </div> <!-- form-group// -->
               <div class="form-group input-group">
    	<div class="input-group-prepend">
    	</div>
                  <div class="col-md-6 mb-4">

                    <select name="role" class="select">
                      <option value="student">Student</option>
                      <option value="teacher">Teacher</option>
                      <option value="admin">Admin</option>

                    </select>

                  </div>
  		<form:hidden value="/images/Generic-Profile.png" path="profilePic"/>
  		<form:hidden value="1" path="darkMode"/>
    	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
        <input name="password" class="form-control" placeholder="Password" type="password">
    </div> <!-- form-group// -->   
    	
    	    	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
        <input name="passwordConfirmation" class="form-control" placeholder="Confirm Password" type="password">
    </div> <!-- form-group// -->   
   </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block"> Create Account  </button>
    </div> <!-- form-group// -->      

    </form:form>
    

</div>
</body>
</html>