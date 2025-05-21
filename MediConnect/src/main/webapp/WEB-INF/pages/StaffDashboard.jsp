<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />

  
  
  <!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CustomerDashboard.css" />
		<link
		      rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
		    />
		<title>MediConnect - Staff</title>
		</head>
		<body>
		<jsp:include page="header.jsp"/>
		
		<section class="main-content">
		      <jsp:include page="leftNavigation.jsp"/>
		
		      <div class="right-content">
        <div class="top-div">
          <div class="left">
            <h1>Welcome to Staff Dashboard</h1>
            <p>Here you can manage your profile and appointments.</p>
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
                <p><strong>First Name :</strong> ${userObj.user_first_name }</p>
                <p><strong>Username:</strong> ${username }</p>
                <p><strong>Location:</strong> ${userObj.user_location }</p>
                <p><strong>Gender:</strong> ${userObj.user_gender }</p>
              </div>
              <div class="right">
                <p><strong>Last Name :</strong> ${userObj.user_last_name }</p>
                <p><strong>Phone No:</strong> ${userObj.user_phonenumber }</p>
                <p><strong>Birth Date:</strong> ${userObj.user_dob }</p>
              </div>
            </div>
            <div class="buttonsofwelcome">
              <button class="edit" onclick="window.location.href='UserEditProfile'">Edit Profile</button>
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
		    
		</body>
		</html>
  
