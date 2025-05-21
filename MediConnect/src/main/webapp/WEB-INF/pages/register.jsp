<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Set page encoding and content type --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Include JSTL core and functions tag libraries --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<%-- Link external stylesheet for register page --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>

	<%-- Including header --%>
	<jsp:include page="header.jsp" />

	<div class="register">
		<div class="register-container">
			<div class="register-form">

				<%-- Welcome text and Logo --%>
				<div class="form-header">
					<img src="${pageContext.request.contextPath}/images/logoblack.png" />
					<h3>Welcome to Mediconnect</h3>
				</div>

				<%-- Registration form --%>
				<form class="form-section"
					action="${pageContext.request.contextPath}/register" method="post"
					enctype="multipart/form-data">
					<div>

						<%-- First row: First name and Last name --%>
						<div class="form-row">
							<div class="form-column">
								<label for="firstname">First Name</label> <input type="text"
									id="firstname" name="firstname" required>
							</div>
							<div class="form-column">
								<label for="lastname">Last Name</label> <input type="text"
									id="lastname" name="lastname" required>
							</div>
						</div>

						<%-- Second row: Username and Location --%>
						<div class="form-row">
							<div class="form-column">
								<label for="username">Username</label> <input type="text"
									id="username" name="username" required>
							</div>
							<div class="form-column">
								<label for="location">Location</label> <input type="text"
									id="location" name="location" required>
							</div>
						</div>

						<%-- Third row: Email and Phone number --%>
						<div class="form-row">
							<div class="form-column">
								<label for="email">Email</label> <input type="email" id="email"
									name="email" required>
							</div>
							<div class="form-column">
								<label for="phoneNumber">Phone Number</label> <input type="text"
									id="phoneNumber" name="phoneNumber" required>
							</div>
						</div>

						<%-- Fourth row: Gender and Date of Birth --%>
						<div class="form-row">
							<div class="form-column">
								<label for="gender">Gender</label> <select name="gender"
									id="gender">
									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Others">Others</option>
								</select>
							</div>
							<div class="form-column">
								<label for="dateOfBirth">Date of Birth</label> <input
									type="date" id="dateOfBirth" name="dateOfBirth" required>
							</div>
						</div>

						<%-- Fifth row: Password and Retype password --%>
						<div class="form-row">
							<div class="form-column">
								<label for="password">Password</label> <input type="password"
									id="password" name="password" required>
							</div>
							<div class="form-column">
								<label for="retypePassword">Retype Password</label> <input
									type="password" id="retypePassword" name="retypePassword"
									required>
							</div>
						</div>

						<%-- Sixth row: Profile picture upload --%>
						<div class="form-row image-row">
							<div class="form-column">
								<label for="image"
									style="border: 1px solid blue; padding: 10px 20px; cursor: pointer;">
									Add Profile Picture</label> <input type="file" id="image" name="image"
									style="display: none;" required>
							</div>
						</div>
					</div>

					<%-- Submit button and login redirect --%>
					<div class="signup-buttons">
						<c:if test="${not empty error}">
							<p style="text-align: center; color: red;">${error }</p>
						</c:if>
						<button class="signup" type="submit">Sign up</button>
						<p>
							Already have an account? <a href="login">Login</a> here
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%-- Including footer --%>
	<jsp:include page="footer.jsp" />

</body>
</html>
