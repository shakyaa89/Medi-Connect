<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Variables for role, firstname, lastname, fullname --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />
<c:set var="fullName"
	value="${userObj.user_first_name } ${userObj.user_last_name }" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/leftNavigation.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
</head>
<body>

	<%-- Checking the user role --%>
	<c:choose>

		<%-- if the role is Admin --%>
		<c:when test="${currentRole == 'Admin'}">
			<div class="container">
				<div class="sidebar">
					<%-- Image and Name section --%>
					<div class="profile-section">
						<img
							src="${pageContext.request.contextPath}/images/Profiles/${userObj.user_image}"
							alt="${userObj.user_image }" />
						<p class="profile-name">${fullName}</p>
					</div>

					<%-- Navigation menu for Admin --%>
					<ul class="nav-list">

						<li>
							<button class="dropdown-btn"
								onclick="window.location.href='AdminDashboard'">
								<div class="btn-flex">
									<span><i class="fa-solid fa-house"></i></span> Dashboard
								</div>
							</button>
						</li>

						<%-- Doctors --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-user-doctor"></i></span> Doctors
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="AdminDoctorList">All Doctors</a></li>
								<li><a href="AdminAddDoctor">Add Doctor</a></li>
							</ul>
						</li>

						<%-- Patients --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-hospital-user"></i></span> Patients
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="CustomerList">All Patients</a></li>
							</ul>
						</li>

						<%-- Staff --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-user-nurse"></i></span> Staff
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="AdminStaffList">All Staff</a></li>
								<li><a href="AdminAddStaff">Add Staff</a></li>
							</ul>
						</li>

						<%-- Bookings --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-bookmark"></i></span> Bookings
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="AppointmentList">View Appointments</a></li>
								<li><a href="UpdateAppointmentList">Update Appointment</a></li>
							</ul>
						</li>

						<%-- Reports --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-clipboard"></i></span> Reports
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="#">Generate</a></li>
								<li><a href="#">View</a></li>
							</ul>
						</li>

						<%-- Account --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-gear"></i></span> Account
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="UserEditProfile">Edit Profile</a></li>
								<li><a href="ChangePassword">Settings</a></li>
							</ul>
						</li>

					</ul>
				</div>
			</div>
		</c:when>

		<c:when test="${currentRole == 'Staff'}">
			<div class="container">
				<div class="sidebar">
					<%-- Image and Name section --%>
					<div class="profile-section">
						<img
							src="${pageContext.request.contextPath}/images/Profiles/${userObj.user_image}"
							alt="${userObj.user_image}" />
						<p class="profile-name">${fullName}</p>
					</div>

					<%-- Navigation menu for Admin --%>
					<ul class="nav-list">

						<li>
							<button class="dropdown-btn"
								onclick="window.location.href='StaffDashboard'">
								<div class="btn-flex">
									<span><i class="fa-solid fa-house"></i></span> Dashboard
								</div>
							</button>
						</li>

						<%-- Patients --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-hospital-user"></i></span> Patients
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="CustomerList">All Patients</a></li>
							</ul>
						</li>

						<%-- Bookings --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-bookmark"></i></span> Bookings
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="AppointmentList">View Appointments</a></li>
								<li><a href="UpdateAppointmentList">Update Appointments</a></li>
							</ul>
						</li>

						<%-- Account --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-gear"></i></span> Account
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="UserEditProfile">Edit Profile</a></li>
								<li><a href="ChangePassword">Settings</a></li>
							</ul>
						</li>

					</ul>
				</div>
			</div>
		</c:when>

		<%-- If the role is Customer --%>
		<c:otherwise>
			<div class="container">
				<div class="sidebar">
					<%-- Profile Image and name --%>
					<div class="profile-section">
						<img
							src="${pageContext.request.contextPath}/images/Profiles/${userObj.user_image }"
							alt="${userObj.user_image }" />
						<p class="profile-name">${fullName}</p>
					</div>

					<%-- Navigation list for regular users --%>
					<ul class="nav-list">

						<li>
							<button class="dropdown-btn"
								onclick="window.location.href='CustomerDashboard'">
								<div class="btn-flex">
									<span><i class="fa-solid fa-house"></i></span> Dashboard
								</div>
							</button>
						</li>

						<%-- Book an appointment --%>

						<li>
							<button class="dropdown-btn"
								onclick="window.location.href='CustomerDoctorList'">
								<div class="btn-flex">
									<span><i class="fa-solid fa-user-doctor"></i></span> Book an
									Appointment
								</div>
							</button>
						</li>

						<%-- Bookings --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-bookmark"></i></span> Bookings
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="AppointmentList">View Appointments</a></li>
								<li><a href="UpdateAppointmentList">Update Appointments</a></li>
							</ul>
						</li>

						<%-- Account --%>
						<li>
							<button class="dropdown-btn">
								<div class="btn-flex">
									<span><i class="fa-solid fa-gear"></i></span> Account
								</div>
							</button>
							<ul class="dropdown-content">
								<li><a href="UserEditProfile">Edit Profile</a></li>
								<li><a href="ChangePassword">Settings</a></li>
							</ul>
						</li>

					</ul>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

	<%-- JavaScript Link --%>
	<script src="${pageContext.request.contextPath}/js/leftNavAdmin.js"></script>

</body>
</html>
