<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Import JSTL core and functions libraries --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Retrieve session attributes --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="doctorId" value="${userSession.getAttribute('doctorId')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>

<%-- Link to external stylesheet --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/CustomerBookAppointment.css" />
</head>
<body>

	<%-- Include header section --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">

		<%-- Include left side navigation panel --%>
		<jsp:include page="leftNavigation.jsp" />

		<%-- Appointment booking form content --%>
		<div class="list-content">
			<div class="card">
				<h2>Book an Appointment</h2>

				<%-- Form to submit appointment booking details --%>
				<form
					action="${pageContext.request.contextPath}/CustomerBookAppointment"
					method="post">

					<%-- Section for selecting date and time --%>
					<div class="selectors">
						<div class="input-group">
							<label>Select Date</label> <input type="date" name="date"
								id="date" />
						</div>
						<div class="input-group">
							<label>Select Time</label> <input type="time" id="time"
								name="time" placeholder="Choose time" required />
						</div>
					</div>

					<%-- Section for displaying doctor info (non-editable dropdown) --%>
					<div class="selectors">
						<div class="doctor">
							<label for="doctor">Select Doctor</label> <select id="doctors"
								name="doctors" class="select-field" required>
								<c:forEach var="doctor" items="${doctorList }">
									<c:if test="${doctor.doctor_id == doctorId}">
										<option value="${doctor.doctorSpecialization }" disabled
											selected id="doctor">Dr. ${doctor.doctorFirstName }
											${doctor.doctorLastName } (${doctor.doctorSpecialization})</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>

					<%-- Display error message if any --%>
					<c:if test="${not empty error}">
						<div class="selectors">
							<p style="text-align: center; color: red;">${error }</p>
						</div>
					</c:if>

					<%-- Submit button for booking --%>
					<button class="book-btn">Book Appointment</button>
				</form>
			</div>
		</div>
	</section>

	<%-- Include footer section --%>
	<jsp:include page="footer.jsp" />

</body>
</html>
