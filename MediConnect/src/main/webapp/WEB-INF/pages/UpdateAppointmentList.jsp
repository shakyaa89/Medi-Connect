<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Set page language and encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Import JSTL core and functions libraries --%>

<%-- Set session-based variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Link CSS file --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AppointmentList.css" />
<title>MediConnect - Admin</title>
</head>
<body>

	<%-- Include header --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">

		<%-- Include left navigation panel --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="appointments-container">
				<div class="appointments-head">
					<h1>Appointments List</h1>
				</div>

				<%-- Appointment table --%>
				<table>
					<thead>
						<tr>
							<th>Appointment ID</th>
							<%-- Show patient info for Admin or Staff only --%>
							<c:if test="${currentRole == 'Admin' || currentRole == 'Staff' }">
								<th>Patient ID</th>
								<th>Patient Name</th>
							</c:if>
							<th>Doctor Appointed</th>
							<th>Appointment Time</th>
							<th>Appointment Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%-- Show appointments for Customer role --%>
						<c:choose>
							<c:when test="${currentRole == 'Customer' }">
								<c:forEach var="appointment" items="${appointmentList}">
									<c:if test="${appointment.user_id == userObj.user_id }">
										<tr>
											<td>${appointment.appointment_id }</td>
											<td>Dr. ${appointment.doctor_first_name }
												${appointment.doctor_last_name }</td>
											<td>${appointment.appointment_time }</td>
											<td>${appointment.appointment_date }</td>
											<td class="buttons">
												<%-- Edit button for customer --%>
												<form action="UpdateAppointment" method="get">
													<input type="hidden" value="${appointment.appointment_id }"
														id="appointmentId" name="appointmentId"> <input
														type="hidden" value="${appointment.doctor_id }"
														id="doctorId" name="doctorId">
													<button class="form-btn">Edit Appointment</button>
												</form> <%-- Delete button with confirmation --%>
												<form action="UpdateAppointmentList" method="post"
													onsubmit="return confirm('Are you sure you want to delete this appointment?');">
													<input type="hidden" value="${appointment.appointment_id }"
														id="appointmentId" name="appointmentId"> <input
														type="hidden" value="${appointment.user_id }" id="userId"
														name="userId">
													<button class="form-btn">Delete Appointment</button>
												</form>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:when>

							<%-- Show all appointments for Admin or Staff --%>
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
										<td class="buttons">
											<%-- Edit button for admin/staff --%>
											<form action="UpdateAppointment" method="get">
												<input type="hidden" value="${appointment.appointment_id }"
													id="appointmentId" name="appointmentId"> <input
													type="hidden" value="${appointment.doctor_id }"
													id="doctorId" name="doctorId">
												<button class="form-btn">Edit Appointment</button>
											</form> <%-- Delete button with confirmation --%>
											<form action="UpdateAppointmentList" method="post"
												onsubmit="return confirm('Are you sure you want to delete this appointment?');">
												<input type="hidden" value="${appointment.appointment_id }"
													id="appointmentId" name="appointmentId"> <input
													type="hidden" value="${appointment.user_id }" id="userId"
													name="userId">
												<button class="form-btn">Delete Appointment</button>
											</form>
										</td>
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
