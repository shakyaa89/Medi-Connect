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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboard.css" />
		<link
		      rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
		    />
		<title>MediConnect - Admin</title>
		</head>
		<body>
		<jsp:include page="header.jsp"/>
		
		
		<section class="main-content">
		      <jsp:include page="leftNavigation.jsp"/>
		
		      <div class="right-content">
		        <div class="top-div">
		          <div class="left">
		            <h1>Welcome to Admin Dashboard</h1>
		            <p>Here you can manage the system.</p>
		          </div>
		          <div class="right">
		            <img src="${pageContext.request.contextPath}/images/profile.jpg" alt="" width="200px" />
		          </div>
		        </div>
		
		        <div class="mid-div">
		          <div class="info-div appointment">
		            <i class="fa-solid fa-bookmark"></i>
		            <h4>Appointment</h4>
		            <p>500</p>
		          </div>
		          <div class="info-div staff">
		            <i class="fa-solid fa-user-nurse"></i>
		            <h4>Staff</h4>
		            <p>500</p>
		          </div>
		          <div class="info-div doctor">
		            <i class="fa-solid fa-user-doctor"></i>
		            <h4>Doctors</h4>
		            <p>500</p>
		          </div>
		          <div class="info-div patients">
		            <i class="fa-solid fa-hospital-user"></i>
		            <h4>Patients</h4>
		            <p>500</p>
		          </div>
		        </div>
		        <div class="bottom-div">
		          <div class="info-div-btm">
		            <h4>Patients by Gender</h4>
		
		            <div class="male-female">
		              <div class="gender-div male">
		                <i class="fa-solid fa-person"></i>
		                <h4>Male</h4>
		                <p>500</p>
		              </div>
		              <div class="gender-div female">
		                <i class="fa-solid fa-person-dress"></i>
		                <h4>Female</h4>
		                <p>500</p>
		              </div>
		            </div>
		          </div>
		          <div class="info-div-btm">
		            <i class="fa-solid fa-dollar-sign"></i>
		            <h4>Revenue</h4>
		            <p>9000</p>
		          </div>
		        </div>
		      </div>
		    </section>
		    
		</body>
		</html>
  
