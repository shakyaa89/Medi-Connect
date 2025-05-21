<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Set session-related variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<%-- Include CSS stylesheets --%>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/AdminAddStaff.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
		
	<title>MediConnect - Admin</title>
</head>
<body>

	<%-- Include header --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">
		
		<%-- Include left navigation panel --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="add-staff-container">
				<%-- Heading and form section title --%>
				<div class="add-staff-head">
					<h1>Edit Staff</h1>
					<h3>Personal Details</h3>
				</div>

				<div class="add-staff-form-container">
					<%-- Staff edit form --%>
					<form action="AdminEditStaff" method="post"
						enctype="multipart/form-data" id="editProfileForm">
						
						<%-- First row of fields: names, username, gender --%>
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
									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Others">Others</option>
								</select>
							</div>
						</div>

						<%-- Second row: location, DOB, email, phone --%>
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
								<label for="email">Email</label>
								<input type="email" id="email" name="email" required>
							</div>
							<div class="form-column">
								<label for="phoneNumber">Phone Number</label>
								<input type="tel" id="phoneNumber" name="phoneNumber" required>
							</div>
						</div>

						<%-- Row for profile picture upload --%>
						<div class="form-row">
							<div class="form-column">
								<label for="image"
									style="border: 1px solid blue; padding: 10px 20px; cursor: pointer;">
									Add Profile Picture
								</label>
								<input type="file" id="image" name="image" style="display: none;" required>
							</div>
						</div>

						<%-- Buttons row: Clear and Submit --%>
						<div class="form-row">
							<div class="buttons">
								<button class="form-buttons" type="button" onclick="clearProfileFields()">Clear</button>
								<button class="form-buttons" type="submit">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<%-- Include script for admin navigation --%>
	<script src="${pageContext.request.contextPath}/js/leftNavAdmin.js"></script>

	<%-- Script to populate form with existing staff data --%>
	<script>
		window.addEventListener("DOMContentLoaded", function() {
			document.getElementById("firstName").value = "${staffObj.user_first_name}";
			document.getElementById("lastName").value = "${staffObj.user_last_name}";
			document.getElementById("username").value = "${staffObj.user_username}";
			document.getElementById("gender").value = "${staffObj.user_gender}";
			document.getElementById("location").value = "${staffObj.user_location}";
			document.getElementById("date-of-birth").value = "${staffObj.user_dob}";
			document.getElementById("email").value = "${staffObj.user_email}";
			document.getElementById("phoneNumber").value = "${staffObj.user_phonenumber}";
		});
	</script>
</body>
</html>
