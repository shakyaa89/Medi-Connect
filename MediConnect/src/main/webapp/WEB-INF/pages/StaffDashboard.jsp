<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Define page content type and character encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Import JSTL core and functions libraries --%>

<%-- Set session and user-related variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Link custom stylesheet for Staff Dashboard --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/StaffDashboard.css" />
<%-- Link external Font Awesome icons --%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<title>MediConnect - Staff</title>
</head>
<body>

	<%-- Include header section --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">
		<%-- Include left-side navigation menu --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="right-content">
			<%-- Top welcome section with user greeting and image --%>
			<div class="top-div">
				<div class="left">
					<h1>Welcome to Staff Dashboard</h1>
					<p>Here you can manage your profile and appointments.</p>
				</div>
				<div class="right">
					<img src="${pageContext.request.contextPath}/images/profile.jpg"
						alt="" width="200px" />
				</div>
			</div>

			<%-- Middle section displaying account details --%>
			<div class="mid-div">
				<div class="account-box">
					<h2>Account Details</h2>
					<div class="details">
						<div class="left">
							<p>
								<strong>First Name :</strong> ${userObj.user_first_name }
							</p>
							<p>
								<strong>Username:</strong> ${username }
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
					<%-- Buttons for editing profile and changing password --%>
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

			<%-- Bottom section showing gender stats and total appointments --%>
			<div class="bottom-div">
				<div class="info-div-btm">
					<h4>Patients by Gender</h4>

					<div class="male-female">
						<div class="gender-div male">
							<i class="fa-solid fa-person"></i>
							<h4>Male</h4>
							<p>${dashboardNumbers.numOfMaleCustomer }</p>
						</div>
						<div class="gender-div female">
							<i class="fa-solid fa-person-dress"></i>
							<h4>Female</h4>
							<p>${dashboardNumbers.numOfFemaleCustomer }</p>
						</div>
					</div>
				</div>

				<%-- Appointment count summary --%>
				<div class="info-div appointment">
					<i class="fa-solid fa-bookmark"></i>
					<h4>Appointment</h4>
					<p>${dashboardNumbers.numOfAppointment }</p>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
