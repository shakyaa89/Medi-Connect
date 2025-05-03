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
	                    <c:forEach var="doctor" items="${doctorList}">
		                    <tr>
		                        <td>${doctor.doctor_id}</td>
		                        <td>${doctor.doctorFirstName} ${doctor.doctorLastName }</td>
		                        <td>${doctor.doctorGender}</td>
		                        <td>${doctor.doctorSpecialization}</td>
								<td><button class="add-btn">Edit Doctor</button></td>
		                    </tr>
	                	</c:forEach>
                        
                    </tbody>
                </table>
            </div>

        </div>
</section>
</body>
</html>