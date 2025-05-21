<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Get session object --%>
<c:set var="userSession" value="${pageContext.session}" />
<%-- Get current logged-in username --%>
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<%-- Get current user role --%>
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<%-- Get application context path --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Link to CSS for Admin Add Doctor page styling --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AdminAddDoctor.css" />
<%-- Link to CSS for error message styling --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
<%-- Link to FontAwesome icons --%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<title>MediConnect - Admin</title>
</head>
<body>
	<%-- Include header section --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">
		<%-- Include left navigation menu --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="add-doctor-container">
				<div class="add-doctor-head">
					<h1>Add Doctor</h1>
					<h3>Personal Details</h3>
				</div>
				<div class="add-doctor-form-container">
					<form action="AdminAddDoctor" method="post"
						enctype="multipart/form-data">
						<div class="form-row">
							<div class="form-column">
								<label for="firstName">First Name</label> 
								<input type="text"
									id="firstName" name="firstName" required>
							</div>
							<div class="form-column">
								<label for="lastName">Last Name</label> 
								<input type="text"
									id="lastName" name="lastName" required>
							</div>
							<div class="form-column">
								<label for="email">Email</label> 
								<input type="email" id="email"
									name="email" required>
							</div>
							<div class="form-column">
								<label for="phoneNumber">Phone Number</label> 
								<input type="tel"
									id="phoneNumber" name="phoneNumber" required>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<label for="location">Location</label> 
								<input type="text"
									id="location" name="location" required>
							</div>
							<div class="form-column">
								<label for="specialization">Specialization</label> 
								<input
									type="text" id="specialization" name="specialization" required>
							</div>
							<div class="form-column">
								<label for="experience">Experience</label> 
								<input type="text"
									id="experience" name="experience" required>
							</div>
							<div class="form-column">
								<label for="gender">Gender</label> 
								<select name="gender"
									id="gender">
									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Others">Others</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<label for="start-time">Start Time</label> 
								<input type="time"
									id="start-time" name="start-time" required>
							</div>
							<div class="form-column">
								<label for="end-time">End Time</label> 
								<input type="time"
									id="end-time" name="end-time" required>
							</div>
							<div class="form-column">
								<label for="available-days">Available Days</label>
								<div class="days">
									<input type="checkbox" id="WeekDays" name="WeekDays"
										value="Week Days" checked> 
									<label class="day-label"
										for="WeekDays">Week Days</label> 
									<input type="checkbox"
										id="WeekEnd" name="WeekEnd" value="Week End"> 
									<label
										class="day-label" for="WeekEnd">Week End</label>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<%-- Custom styled label to trigger file input --%>
								<label for="image"
									style="border: 1px solid blue; padding: 10px 20px; cursor: pointer;">Add
									Profile Picture</label> 
								<input type="file" id="image" name="doctor-image"
									style="display: none;" required>
							</div>
							<%-- Show error message if any --%>
							<c:if test="${not empty error}">
								<div class="form-column">
									<p style="text-align: center; color: red;">${error }</p>
								</div>
							</c:if>
						</div>
						<div class="form-row">
							<div class="buttons">
								<%-- Reset and Submit buttons --%>
								<button class="form-buttons" type="reset">Clear</button>
								<button class="form-buttons">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	</section>
</body>
</html>
