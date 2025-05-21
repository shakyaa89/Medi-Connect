<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Import JSTL core and functions libraries --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Set session-related variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<%-- Include custom CSS and FontAwesome --%>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/CustomerDashboard.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
	<title>MediConnect - Patient</title>
</head>
<body>

	<%-- Include header section --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">

		<%-- Include left navigation menu --%>
		<jsp:include page="leftNavigation.jsp" />

		<%-- Right side content of the dashboard --%>
		<div class="right-content">

			<%-- Top section: Welcome message and profile image --%>
			<div class="top-div">
				<div class="left">
					<h1>Welcome to Patient Dashboard</h1>
					<p>Here you can manage your profile.</p>
				</div>
				<div class="right">
					<img src="${pageContext.request.contextPath}/images/profile.jpg"
						alt="" width="200px" />
				</div>
			</div>

			<%-- Middle section: Account details --%>
			<div class="mid-div">
				<div class="account-box">
					<h2>Account Details</h2>
					<div class="details">
						<div class="left">
							<p>
								<strong>First Name :</strong> ${userObj.user_first_name }
							</p>
							<p>
								<strong>Username:</strong> ${currentUser }
							</p>
							<p>
								<strong>Location:</strong> ${userObj.user_location }
							</p>
							<p>
								<strong>Gender:</strong> ${userObj.user_gender }
							</p>
						</div>
						<div class="right">
							<p>
								<strong>Last Name :</strong> ${userObj.user_last_name }
							</p>
							<p>
								<strong>Phone No:</strong> ${userObj.user_phonenumber }
							</p>
							<p>
								<strong>Birth Date:</strong> ${userObj.user_dob }
							</p>
						</div>
					</div>

					<%-- Action buttons: Edit profile and change password --%>
					<div class="buttonsofwelcome">
						<button class="edit"
							onclick="window.location.href='UserEditProfile'">Edit
							Profile</button>
						<button class="change"
							onclick="window.location.href='ChangePassword'">Change
							password</button>
					</div>
				</div>
			</div>

			<%-- Bottom section: Dashboard metrics --%>
			<div class="bottom-div">
				<div class="info-div-btm">
					<i class="fa-solid fa-bookmark"></i>
					<h4>Your Appointments</h4>
					<p>${dashboardNumbers.numOfAppointment }</p>
				</div>
				<div class="info-div-btm">
					<i class="fa-solid fa-user-doctor"></i>
					<h4>Doctors Available</h4>
					<p>${dashboardNumbers.numOfDoctor }</p>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
