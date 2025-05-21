<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Declare page language and character encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Import JSTL core and functions libraries --%>

<%-- Set session-based user details --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userObj" value="${userSession.getAttribute('userObj')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Include custom CSS styles --%>
<link rel="stylesheet" href="${contextPath}/css/UserEditProfile.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
<title>MediConnect - Admin</title>
</head>
<body>
	<%-- Include header --%>
	<jsp:include page="header.jsp" />
	<section class="main-content">
		<%-- Include sidebar navigation --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="add-staff-container">
				<div class="add-staff-head">
					<h1>Edit Profile</h1>
					<h3>Personal Details</h3>
				</div>

				<%-- Profile edit form begins --%>
				<div class="add-staff-form-container">
					<form action="UserEditProfile" method="post" id="editProfileForm"
						enctype="multipart/form-data">

						<%-- First row of input fields --%>
						<div class="form-row">
							<div class="form-column">
								<label for="firstName">First Name</label> <input type="text"
									id="firstName" name="firstName" value="" required>
							</div>
							<div class="form-column">
								<label for="lastName">Last Name</label> <input type="text"
									id="lastName" name="lastName" value="" required>
							</div>
							<div class="form-column">
								<label for="username">Username</label> <input type="text"
									id="username" name="username" value="" required>
							</div>
							<div class="form-column">
								<label for="gender">Gender</label> <select name="gender"
									id="gender">
									<option value="${userObj.user_gender }" selected hidden>${userObj.user_gender }</option>
									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Others">Others</option>
								</select>
							</div>
						</div>

						<%-- Second row of input fields --%>
						<div class="form-row">
							<div class="form-column">
								<label for="location">Location</label> <input type="text"
									id="location" name="location" value="" required>
							</div>
							<div class="form-column">
								<label for="date-of-birth">Date of Birth</label> <input
									type="date" id="date-of-birth" name="date-of-birth" value=""
									required>
							</div>
							<div class="form-column">
								<label for="email">Email</label> <input type="email" id="email"
									name="email" value="" required>
							</div>
							<div class="form-column">
								<label for="phoneNumber">Phone Number</label> <input type="tel"
									id="phoneNumber" name="phoneNumber" value="" required>
							</div>
						</div>

						<%-- Profile picture file input --%>
						<div class="form-row">
							<div class="form-column">
								<label for="image"
									style="border: 1px solid blue; padding: 10px 20px; cursor: pointer;">
									Add Profile Picture </label> <input type="file" id="image" name="image"
									style="display: none;" required>
							</div>

							<%-- Display error message if available --%>
							<c:if test="${not empty error}">
								<div class="form-column">
									<p style="text-align: center; color: red;">${error }</p>
								</div>
							</c:if>
						</div>

						<%-- Form buttons (Clear and Submit) --%>
						<div class="form-row">
							<div class="buttons">
								<button class="form-buttons" type="button"
									onclick="clearProfileFields()">Clear</button>
								<button class="form-buttons" type="submit">Submit</button>
							</div>
						</div>
					</form>
				</div>
				<%-- End of form container --%>
			</div>
		</div>
	</section>

	<%-- Include JavaScript for sidebar --%>
	<script src="${pageContext.request.contextPath}/js/leftNavAdmin.js"></script>

	<%-- JavaScript to pre-fill user details from session --%>
	<script>
		window
				.addEventListener(
						"DOMContentLoaded",
						function() {
							document.getElementById("firstName").value = "${userObj.user_first_name}";
							document.getElementById("lastName").value = "${userObj.user_last_name}";
							document.getElementById("username").value = "${currentUser}";
							document.getElementById("gender").value = "${userObj.user_gender}";
							document.getElementById("location").value = "${userObj.user_location}";
							document.getElementById("date-of-birth").value = "${userObj.user_dob}";
							document.getElementById("email").value = "${userObj.user_email}";
							document.getElementById("phoneNumber").value = "${userObj.user_phonenumber}";
						});
	</script>
</body>
</html>
