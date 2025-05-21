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
<%-- Link to CSS for Admin Add Staff page styling --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AdminAddStaff.css" />
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
			<div class="add-staff-container">
				<div class="add-staff-head">
					<h1>Add Staff</h1>
					<h3>Personal Details</h3>
				</div>
				<div class="add-staff-form-container">
					<form action="${contextPath}/AdminAddStaff" method="post"
						enctype="multipart/form-data">
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
								<label for="username">Username</label> <input type="text"
									id="username" name="username" required>
							</div>
							<div class="form-column">
								<label for="gender">Gender</label> <select name="gender"
									id="gender">
									<option value="male">Male</option>
									<option value="female">Female</option>
									<option value="others">Others</option>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-column">
								<label for="location">Location</label> <input type="text"
									id="location" name="location" required>
							</div>
							<div class="form-column">
								<label for="date-of-birth">Date of birth</label> <input
									type="date" id="date-of-birth" name="date-of-birth" required>
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
								<label for="password">Password</label> <input type="password"
									id="password" name="password" required>
							</div>
							<div class="form-column">
								<label for="retype-password">Retype Password</label> <input
									type="password" id="retype-password" name="retype-password"
									required>
							</div>
							<div class="form-column">
								<label for="staff-image">Profile Picture</label>
								<%-- Custom styled label to trigger file input --%>
								<label for="image"
									style="border: 1px solid blue; padding: 10px 20px; cursor: pointer;">Add
									Profile Picture</label> <input type="file" id="image"
									name="staff-image" style="display: none;" required>
							</div>
						</div>
						<div class="form-row">
							<div class="buttons">
								<%-- Clear and Submit buttons --%>
								<button class="form-buttons">Clear</button>
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
