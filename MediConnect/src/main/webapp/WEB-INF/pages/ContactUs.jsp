<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>

<%-- Link to external Contact Us CSS file --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ContactUs.css" />
</head>
<body>

	<%-- Include header section --%>
	<jsp:include page="header.jsp" />

	<%-- Contact Us form container --%>
	<div class="contactus-container">

		<%-- Page header and instructions --%>
		<div class="contactus-header">
			<h1>Contact Us</h1>
			<p>If you have any questions or concerns, feel free to reach out
				to us using the form below:</p>
		</div>

		<%-- Contact form section --%>
		<div class="contactus-form">
			<form action="/submit-contact" method="post">

				<%-- First and Last Name input fields --%>
				<div class="form-row">
					<div class="form-column">
						<label for="firstName">First Name</label> <input type="text"
							id="firstName" name="firstName" required />
					</div>
					<div class="form-column">
						<label for="lastName">Last Name</label> <input type="text"
							id="lastNAme" name="lastName" required />
					</div>
				</div>

				<%-- Email and Phone Number input fields --%>
				<div class="form-row">
					<div class="form-column">
						<label for="email">Email</label> <input type="email" id="email"
							name="email" required />
					</div>
					<div class="form-column">
						<label for="phoneNumber">Number</label> <input type="number"
							id="phoneNumber" name="phoneNumber" required />
					</div>
				</div>

				<%-- Message textarea input --%>
				<div class="form-row">
					<div class="form-column">
						<label for="message">Message:</label><br />
						<textarea class="message-box" id="message" name="message" rows="5"
							required></textarea>
						<br />
						<br />
					</div>
				</div>

				<%-- Submit button --%>
				<div class="buttons">
					<button type="submit">Submit</button>
				</div>

			</form>
		</div>
	</div>

	<%-- Include footer section --%>
	<jsp:include page="footer.jsp" />

</body>
</html>
