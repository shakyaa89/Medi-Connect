<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CustomerDoctorList.css" />
<title>MediConnect</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<section class="main-content">
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="doctor-container">
				<div class="doctor-head">
					<h1>Available Doctors</h1>
					<form action="SearchController" method="get">
						<input type="text" name="search" placeholder="Search Doctors">
						<button type="submit" class="search-btn">Search</button>
						<a href="CustomerDoctorList"><button type="button"
								class="search-btn">Reset</button></a>
					</form>
				</div>
				<div class="card-container">
					<c:forEach var="doctor" items="${doctorList}">
						<div class="card">
							<img
								src="${pageContext.request.contextPath}/images/DoctorProfiles/${doctor.doctorImage }"
								alt="${doctor.doctorImage }">
							<div class="doctor-name">Dr. ${doctor.doctorFirstName }
								${doctor.doctorLastName }</div>
							<div class="specialization">${doctor.doctorSpecialization }</div>
							<c:forEach var="doctorAv" items="${doctorAvailabilityList}">
								<c:if test="${doctorAv.doctor_id == doctor.doctor_id }">
									<div class="specialization">${doctorAv.start_time }-
										${doctorAv.end_time }</div>

									<form action="CustomerBookAppointment" method="get">
										<input type="hidden" name="doctorId"
											value="${doctor.doctor_id}" />
											<input type="hidden" name="startTime"
											value="${doctorAv.start_time}" />
											<input type="hidden" name="endTime"
											value="${doctorAv.end_time}" />
										<button type="submit" class="book-btn">Book</button>
									</form>
								</c:if>
							</c:forEach>
						</div>

					</c:forEach>

				</div>
			</div>
		</div>
	</section>

</body>
</html>