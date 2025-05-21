<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%-- Link to footer stylesheet --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>

	<%-- Footer section starts --%>
	<footer class="website-footer">
		<div class="footer-container">

			<%-- About MediConnect column --%>
			<div class="footer-column">
				<h2>MediConnect</h2>
				<p>Your trusted partner in comprehensive healthcare. Committed
					to providing quality care through every stage of life</p>
				<p>At MedConnect, we are dedicated to being your lifelong
					healthcare partner. Our compassionate team delivers personalized,
					comprehensive care tailored to every stage of life.</p>
			</div>

			<%-- Contact information column --%>
			<div class="footer-column">
				<h2 style="margin-left: 10px;">Contact</h2>
				<p>
					<span style="margin-right: 10px;"><i
						class="fa-solid fa-location-dot"></i></span> Kathmandu, Nepal
				</p>
				<p>
					<span style="margin-right: 10px;"><i
						class="fa-solid fa-envelope"></i></span>contactus@gmail.com
				</p>
				<p>
					<span style="margin-right: 10px;"><i
						class="fa-solid fa-phone"></i></span>+977-9000000000
				</p>
			</div>

			<%-- Opening hours column --%>
			<div class="footer-column">
				<h2>Opening hours</h2>

				<div class="footer-row">
					<p>Mon-Thu:</p>
					<p>8am-9pm</p>
				</div>
				<div class="footer-line"></div>

				<div class="footer-row">
					<p>Fri-Sat:</p>
					<p>8am-2pm</p>
				</div>
				<div class="footer-line"></div>

				<div class="footer-row">
					<p>Sunday:</p>
					<p>9am-10pm</p>
				</div>
				<div class="footer-line"></div>
			</div>
		</div>

		<%-- Copyright notice --%>
		<div class="copyrights">
			<p>&copy; 2025 Copyright, Mediconnect.com</p>
		</div>
	</footer>

</body>
</html>
