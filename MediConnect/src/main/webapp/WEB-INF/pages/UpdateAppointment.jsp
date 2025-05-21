<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Set page language and encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Import JSTL core and functions libraries --%>

<%-- Set session-based variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="doctorId" value="${userSession.getAttribute('doctorId')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<%-- Link to external CSS stylesheet --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/CustomerBookAppointment.css" />
</head>
<body>

	<%-- Include header section --%>
	<jsp:include page="header.jsp" />
	
	<section class="main-content">
		<%-- Include left navigation panel --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="card">
				<h2>Update Appointment</h2>

				<%-- Appointment update form --%>
				<form action="UpdateAppointment" method="post">
					<div class="selectors">
						<%-- Date selection input --%>
						<div class="input-group">
							<label>Select Date</label> 
							<input type="date" name="date"
								id="date" value="${appointmentDate }" required />
						</div>

						<%-- Time selection input --%>
						<div class="input-group">
							<label>Select Time</label> 
							<input type="time" id="time"
								name="time" placeholder="Choose time"
								value="${appointmentTime }" required />
						</div>
					</div>

					<div class="selectors">
						<%-- Doctor dropdown (disabled, preselected) --%>
						<div class="doctor">
							<label for="doctor">Select Doctor</label> 
							<select id="doctors"
								name="doctors" class="select-field" required>
								<c:forEach var="doctor" items="${doctorList }">
									<c:if test="${doctor.doctor_id == doctorId}">
										<option value="${doctor.doctorSpecialization }" disabled
											selected id="doctor">
											Dr. ${doctor.doctorFirstName } ${doctor.doctorLastName } 
											(${doctor.doctorSpecialization})
										</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>

					<%-- Show error message if exists --%>
					<c:if test="${not empty error}">
						<div class="selectors">
							<p style="text-align: center; color: red;">${error }</p>
						</div>
					</c:if>

					<%-- Submit button --%>
					<button class="book-btn">Update Appointment</button>
				</form>
			</div>
		</div>
	</section>

	<%-- Include footer section --%>
	<jsp:include page="footer.jsp" />

</body>
</html>
