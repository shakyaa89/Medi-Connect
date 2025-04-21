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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDoctorList.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ErrorMessage.css"/>

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
            <div class="doctor-container">
                <div class="doctor-head">
                    <h1>Doctor List</h1>
                    <form action="">
                        <input type="text" placeholder="Search">
                    </form>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Specification</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>001</td>
                            <td>Dr. Sarah Ali</td>
                            <td>Female</td>
                            <td>Cardiologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Dr. Omar Khan</td>
                            <td>Male</td>
                            <td>Dermatologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Dr. Omar Khan</td>
                            <td>Male</td>
                            <td>Dermatologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Dr. Omar Khan</td>
                            <td>Male</td>
                            <td>Dermatologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Dr. Omar Khan</td>
                            <td>Male</td>
                            <td>Dermatologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Dr. Omar Khan</td>
                            <td>Male</td>
                            <td>Dermatologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        <tr>
                            <td>002</td>
                            <td>Dr. Omar Khan</td>
                            <td>Male</td>
                            <td>Dermatologist</td>
                            <td><button class="add-btn">Edit Doctor</button></td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>

        </div>
</section>
</c:otherwise>
</c:choose>
</body>
</html>