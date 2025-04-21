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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminAddDoctor.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ErrorMessage.css"/>
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
            <div class="add-doctor-container">
                <div class="add-doctor-head">
                    <h1>Add Doctor</h1>
                    <h3>Personal Details</h3>
                </div>
                <div class="add-doctor-form-container">
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
                                <label for="email">Email</label> 
                                <input type="email" id="email" name="email" required>
                            </div>
                            <div class="form-column">
                                <label for="phoneNumber">Phone Number</label> 
                                <input type="tel" id="phoneNumber" name="phoneNumber" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="location">Location</label> 
                                <input type="text" id="location" name="location" required>
                            </div>
                            <div class="form-column">
                                <label for="specialization">Specialization</label> 
                                <input type="text" id="specialization" name="specialization" required>
                            </div>
                            <div class="form-column">
                                <label for="experience">Experience</label> 
                                <input type="text" id="experience" name="experience" required>
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
                                <label for="start-time">Start Time</label> 
                                <input type="text" id="start-time" name="start-time" required>
                            </div>
                            <div class="form-column">
                                <label for="end-time">End Time</label> 
                                <input type="text" id="end-time" name="end-time" required>
                            </div>
                            <div class="form-column">
                                <label for="available-days">Available Days</label>
                                <div class="days">
                                    <input type="checkbox" id="mon" name="days" value="Mon" checked>
                                    <label class="day-label" for="mon">Mon</label>
                    
                                    <input type="checkbox" id="tue" name="days" value="Tue" checked>
                                    <label class="day-label" for="tue">Tue</label>
                    
                                    <input type="checkbox" id="wed" name="days" value="Wed" checked>
                                    <label class="day-label" for="wed">Wed</label>
                    
                                    <input type="checkbox" id="thu" name="days" value="Thu" checked>
                                    <label class="day-label" for="thu">Thu</label>
                    
                                    <input type="checkbox" id="fri" name="days" value="Fri" checked>
                                    <label class="day-label" for="fri">Fri</label>
                    
                                    <input type="checkbox" id="sat" name="days" value="Sat" checked>
                                    <label class="day-label" for="sat">Sat</label>
                    
                                    <input type="checkbox" id="sun" name="days" value="Sun" checked>
                                    <label class="day-label" for="sun">Sun</label>
                                </div>
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
  
