<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>

	<jsp:include page="header.jsp" />

	<section class="hero">
		<div class="hero-content">
			<div class="hero-text">
				<h1>
					Your Health, <br> Your Schedule, <br> Our Priority
				</h1>
				<button class="appointment-btn">Book an Appointment</button>
			</div>
		</div>
	</section>

	<section class="doctors-section">
		<h2>Our Doctors</h2>
		<div class="underline"></div>
		<div class="doctors-grid">
			<c:forEach var="doctor" items="${doctorList}">
				<div class="doctor-card">
					<div class="doctor-image">
						<img
							src="${pageContext.request.contextPath}/images/DoctorProfiles/${doctor.doctorImage }"
							alt="${doctor.doctorImage }">
					</div>
					<div class="doctor-info">
						<h3>Dr. ${doctor.doctorFirstName } ${doctor.doctorLastName }</h3>

						<c:forEach var="doctorAv" items="${doctorAvailabilityList}">
							<c:if test="${doctorAv.doctor_id == doctor.doctor_id }">
								<p>${doctorAv.start_time } - ${doctorAv.end_time }</p>
								<p>${doctor.doctorSpecialization }</p>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<section class="healthcare-section">
		<div class="content-container">
			<div class="section-content">
				<h2 class="section-title">Your Trusted Partner in Health</h2>
				<p class="section-lead">Comprehensive Care for Every Stage of
					Life. At MedConnect</p>
				<p class="section-text">We understand that health needs evolve
					with time. Our mission is to provide tailored, compassionate care
					for individuals and families, no matter where they are on life’s
					journey.</p>
				<div class="healthcare-section-buttons">
					<button class="appointment-btn">Book an Appointment</button>
					<button class="appointment-btn">Contact us</button>
				</div>
			</div>
			<div class="healthcare-section-image">
				<img src="${pageContext.request.contextPath}/images/indeximg.jpg"
					alt="">
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />

</body>
</html>