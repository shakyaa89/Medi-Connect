<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="firstName" value="${userSession.getAttribute('firstName')}" />
<c:set var="lastName" value="${userSession.getAttribute('lastName')}" />
<c:set var="gender" value="${userSession.getAttribute('gender')}" />
<c:set var="DOB" value="${userSession.getAttribute('DOB')}" />
<c:set var="location" value="${userSession.getAttribute('location')}" />
<c:set var="phoneNumber" value="${userSession.getAttribute('phoneNumber')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

  
  
  <!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CustomerDashboard.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ErrorMessage.css"/>
		<link
		      rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
		    />
		<title>MediConnect - Admin</title>
		</head>
		<body>
		<jsp:include page="header.jsp"/>
		
		<c:choose>
		    <c:when test="${empty currentUser}">
			<div class="error-message">
				<h2>Access Denied</h2>
		        <p>You do not have permission to access the Customer dashboard.</p>
		        <p><a href="login">You are not logged in!!</a></p>
			</div>
		    </c:when>
		
		    <c:when test="${currentRole != 'Customer'}">
		        <div class="error-message">
				<h2>Access Denied</h2>
		        <p>You do not have permission to access the Customer dashboard.</p>
		        <p><a href="index">You are not a Customer!!</a></p>
			</div>
		    </c:when>
		    
		     <c:otherwise>
		
		<section class="main-content">
		      <jsp:include page="leftNavigation.jsp"/>
		
		      <div class="right-content">
        <div class="top-div">
          <div class="left">
            <h1>Welcome to Customer Dashboard</h1>
            <p>Here you can manage your profile.</p>
          </div>
          <div class="right">
            <img src="${pageContext.request.contextPath}/images/profile.jpg" alt="" width="200px" />
          </div>
        </div>

        <div class="mid-div">
          <div class="account-box">
            <h2>Account Details</h2>
            <div class="details">
              <div class="left">
                <p><strong>First Name :</strong> ${firstName }</p>
                <p><strong>Username:</strong> ${username }</p>
                <p><strong>Location:</strong> ${location }</p>
                <p><strong>Gender:</strong> ${gender }</p>
              </div>
              <div class="right">
                <p><strong>Last Name :</strong> ${lastName }</p>
                <p><strong>Phone No:</strong> ${phoneNumber }</p>
                <p><strong>Birth Date:</strong> ${DOB }</p>
              </div>
            </div>
            <div class="buttonsofwelcome">
              <button class="edit">Edit Profile</button>
              <button class="change" onclick="window.location.href='ChangePassword'">Change password</button>
            </div>
          </div>
        </div>

        <div class="bottom-div">
          <div class="info-div-btm">
            <i class="fa-solid fa-dollar-sign"></i>
            <h4>Revenue</h4>
            <p>9000</p>
          </div>
          <div class="info-div-btm">
            <i class="fa-solid fa-dollar-sign"></i>
            <h4>Revenue</h4>
            <p>9000</p>
          </div>
        </div>
      </div>
		    </section>
		    </c:otherwise>
		</c:choose>
		
		
		    
		</body>
		</html>
  
