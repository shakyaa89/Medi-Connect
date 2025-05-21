<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Set session and context variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Include stylesheets for appointment list and error messages --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AppointmentList.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
<title>MediConnect - Admin</title>
</head>
<body>

	<%-- Include page header --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">

		<%-- Include left navigation menu --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="appointments-container">

				<%-- Header for appointments section --%>
				<div class="appointments-head">
					<h1>Appointments List</h1>
				</div>

				<%-- Appointment list table --%>
				<table>
					<thead>
						<tr>
							<th>Appointment ID</th>
							<%-- Show patient details only for Admin and Staff --%>
							<c:if test="${currentRole == 'Admin' || currentRole == 'Staff' }">
								<th>Patient ID</th>
								<th>Patient Name</th>
							</c:if>
							<th>Doctor Appointed</th>
							<th>Appointment Time</th>
							<th>Appointment Date</th>
						</tr>
					</thead>
					<tbody>
						<%-- Display appointments based on user role --%>
						<c:choose>
							<%-- For Customers: show only their own appointments --%>
							<c:when test="${currentRole == 'Customer' }">
								<c:forEach var="appointment" items="${appointmentList}">
									<c:if test="${appointment.user_id == userObj.user_id}">
										<tr>
											<td>${appointment.appointment_id }</td>
											<td>Dr. ${appointment.doctor_first_name }
												${appointment.doctor_last_name }</td>
											<td>${appointment.appointment_time }</td>
											<td>${appointment.appointment_date }</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:when>
							<%-- For Admin/Staff: show all appointments with full info --%>
							<c:otherwise>
								<c:forEach var="appointment" items="${appointmentList}">
									<tr>
										<td>${appointment.appointment_id }</td>
										<td>${appointment.user_id }</td>
										<td>${appointment.user_first_name }
											${appointment.user_last_name }</td>
										<td>Dr. ${appointment.doctor_first_name }
											${appointment.doctor_last_name }</td>
										<td>${appointment.appointment_time }</td>
										<td>${appointment.appointment_date }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</tbody>
				</table>
			</div>
		</div>
	</section>

</body>
</html>
