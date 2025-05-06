<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:forEach var="doctor" items="${doctorList}">
	<c:if test="${doctor.doctor_id == doctorId }">
		<c:set var="doctorFirstname" value="${doctor.doctorFirstName}" />
		<c:set var="doctorLastname" value="${doctor.doctorLastName}" />
		<c:set var="doctorEmail" value="${doctor.doctorEmail}" />
		<c:set var="doctorPhonenum" value="${doctor.doctorPhoneNumber}" />
		<c:set var="doctorLocation" value="${doctor.doctorAddress}" />
		<c:set var="doctorSpecialization"
			value="${doctor.doctorSpecialization}" />
		<c:set var="doctorExperience" value="${doctor.doctorExperience}" />
	</c:if>
</c:forEach>

<c:forEach var="doctorAv" items="${doctorAvailabilityList}">
	<c:if test="${doctorAv.doctor_id == doctorId}">
		<c:set var="doctorStart" value="${doctorAv.start_time}" />
		<c:set var="doctorEnd" value="${doctorAv.end_time}" />
		<c:set var="doctorDays" value="${doctorAv.doctor_available_day}" />
	</c:if>
</c:forEach>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AdminEditDoctor.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<title>MediConnect - Admin</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<section class="main-content">
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="add-doctor-container">
				<div class="add-doctor-head">
					<h1>Add Doctor</h1>
					<h3>Personal Details</h3>
				</div>
				<div class="add-doctor-form-container">
					<form action="AdminEditDoctor" method="post"
						enctype="multipart/form-data" id="doctorEditForm">
						<div class="form-row">
							<div class="form-column">
								<label for="firstName">First Name</label> <input type="text"
									id="firstName" name="firstName" required>
							</div>
							<div class="form-column">
								<label for="lastName">Last Name</label> <input type="text"
									id="lastName" name="lastName" required>
							</div>
							<div class="form-column">
								<label for="email">Email</label> <input type="email" id="email"
									name="email" required>
							</div>
							<div class="form-column">
								<label for="phoneNumber">Phone Number</label> <input type="tel"
									id="phoneNumber" name="phoneNumber" required>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<label for="location">Location</label> <input type="text"
									id="location" name="location" required>
							</div>
							<div class="form-column">
								<label for="specialization">Specialization</label> <input
									type="text" id="specialization" name="specialization" required>
							</div>
							<div class="form-column">
								<label for="experience">Experience</label> <input type="text"
									id="experience" name="experience" required>
							</div>
							<div class="form-column">
								<label for="gender">Gender</label> <select name="gender"
									id="gender">
									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Others">Others</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<label for="start-time">Start Time</label> <input type="text"
									id="start-time" name="start-time" required>
							</div>
							<div class="form-column">
								<label for="end-time">End Time</label> <input type="text"
									id="end-time" name="end-time" required>
							</div>
							<div class="form-column">
								<label for="available-days">Available Days</label>
								<div class="days">
									<input type="checkbox" id="WeekDays" name="WeekDays"
										value="Week Days"
										<c:if test="${doctorDays == 'Week Days' || doctorDays == 'Week Days, Week End'}">checked</c:if>>
									<label class="day-label" for="WeekDays">Week Days</label> <input
										type="checkbox" id="WeekEnd" name="WeekEnd" value="Week End"
										<c:if test="${doctorDays == 'Week End' || doctorDays == 'Week Days, Week End'}">checked</c:if>>
									<label class="day-label" for="WeekEnd">Week End</label>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<label for="doctor-image">Picture</label> <input type="file"
									id="doctor-image" name="doctor-image" required>
							</div>
						</div>
						<div class="form-row">
							<div class="buttons">
								<button class="form-buttons" type="button" onclick="clearProfileFieldsDoctor()">Clear</button>
								<button class="form-buttons" type="submit">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</section>
<script src="${pageContext.request.contextPath}/js/leftNavAdmin.js"></script>
	<script>
		window
				.addEventListener(
						"DOMContentLoaded",
						function() {
							document.getElementById("firstName").value = "${doctorFirstname}";
							document.getElementById("lastName").value = "${doctorLastname}";
							document.getElementById("email").value = "${doctorEmail}";
							document.getElementById("phoneNumber").value = "${doctorPhonenum}";
							document.getElementById("location").value = "${doctorLocation}";
							document.getElementById("specialization").value = "${doctorSpecialization}";
							document.getElementById("experience").value = "${doctorExperience}";
							document.getElementById("start-time").value = "${doctorStart}";
							document.getElementById("end-time").value = "${doctorEnd}";
						});
	</script>
</body>
</html>

