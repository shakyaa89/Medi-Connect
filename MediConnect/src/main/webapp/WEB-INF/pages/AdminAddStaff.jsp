<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

  
  
  <!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminAddStaff.css" />
		<link
		      rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
		    />
		<title>MediConnect - Admin</title>
		</head>
		<body>
		
		<c:choose>
		    <c:when test="${empty currentUser}">
			<div class="error-message">
				<h2>Access Denied</h2>
		        <p>You do not have permission to access the admin dashboard.</p>
		        <p><a href="login">You are not logged in!!</a></p>
			</div>
		    </c:when>
		
		    <c:when test="${currentRole != 'Admin'}">
		        <div class="error-message">
				<h2>Access Denied</h2>
		        <p>You do not have permission to access the admin dashboard.</p>
		        <p><a href="index">You are not an admin!!</a></p>
			</div>
		    </c:when>
		    
		     <c:otherwise>
		        <jsp:include page="header.jsp"/>
		
		<section class="main-content">
		      <jsp:include page="leftNavigation.jsp"/>
		      
				<div class="list-content">
            <div class="add-staff-container">
                <div class="add-staff-head">
                    <h1>Add Staff</h1>
                    <h3>Personal Details</h3>
                </div>
                <div class="add-staff-form-container">
                    <form action="">
                        <div class="form-row">
                            <div class="form-column">
                                <label for="firstName">First Name</label> 
                                <input type="text" id="firstName" name="firstName" required>
                            </div>
                            <div class="form-column">
                                <label for="lastName">Last Name</label> 
                                <input type="text" id="lastName" name="lastName" required>
                            </div>
                            <div class="form-column">
                                <label for="username">Username</label> 
                                <input type="text" id="username" name="username" required>
                            </div>
                            <div class="form-column">
                                <label for="gender">Gender</label> 
                                <select name="gender" id="gender">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                    <option value="others">Others</option>
                              </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="location">Location</label> 
                                <input type="text" id="location" name="location" required>
                            </div>
                            <div class="form-column">
                                <label for="date-of-birth">Date of birth</label> 
                                <input type="date" id="date-of-birth" name="date-of-birth" required>
                            </div>
                            <div class="form-column">
                                <label for="role">Role</label> 
                                <input type="text" id="rolel" name="role" required>
                            </div>
                            <div class="form-column">
                                <label for="email">Email</label> 
                                <input type="email" id="email" name="email" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="phoneNumber">Phone Number</label> 
                                <input type="tel" id="phoneNumber" name="phoneNumber" required>
                            </div>
                            <div class="form-column">
                                <label for="password">Password</label> 
                                <input type="password" id="password" name="password" required>
                            </div>
                            <div class="form-column">
                                <label for="retype-password">Retype Password</label> 
                                <input type="password" id="retype-password" name="retype-password" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="buttons">
                                <button class="form-buttons">Clear</button>
                                <button class="form-buttons">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div> 
        </div>
				
		      
		    </section>
		    </c:otherwise>
		</c:choose>
		
		
		    
		</body>
		</html>
  
