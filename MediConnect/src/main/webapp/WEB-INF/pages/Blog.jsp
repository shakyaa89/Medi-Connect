<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<%-- Link to About Us page CSS --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog.css" />
</head>
<body>

	<%-- Include common header --%>
	<jsp:include page="header.jsp" />

	<section class="about-section">
		<div class="container-blog">
			<h1>Health Tips & Articles</h1>
			<p class="filters">
				Topics: <a href="#">All</a> <a href="#">Nutrition</a> <a href="#">Fitness</a>
				<a href="#">Mental Health</a> <a href="#">Lifestyle</a>
			</p>

			<div class="post">
				<img src="${pageContext.request.contextPath}/images/Common Symp.png" alt="Common Symptoms">
				<div class="category">Symptoms</div>
				<h2>How to Identify Early Signs of Common Illnesses</h2>
				<p>Learn how to recognize symptoms early—like fever, fatigue,
					and cough—and when to consult a doctor.</p>
				<a href="#" class="read-more">Keep Reading →</a>
			</div>

			<div class="post">
				<img src="${pageContext.request.contextPath}/images/Doctor.png" alt="Doctor Visit Checklist">
				<div class="category">Doctors Advice</div>
				<h2>What to Prepare Before Visiting a Doctor</h2>
				<p>Bring the right documents, note your symptoms, and ask the
					right questions to make the most of your appointment.</p>
				<a href="#" class="read-more">Keep Reading →</a>
			</div>

			<div class="post">
				<img src="${pageContext.request.contextPath}/images/Telehealth.png" alt="Telehealth Tips">
				<div class="category">Telehealth</div>
				<h2>Getting the Most Out of Your Telehealth Appointment</h2>
				<p>Use a stable internet connection, prepare notes, and follow
					up with your doctor to ensure effective remote care.</p>
				<a href="#" class="read-more">Keep Reading →</a>
			</div>

			<div class="post">
				<img src="${pageContext.request.contextPath}/images/Confused.png" alt="Choosing the Right Doctor">
				<div class="category">General Health</div>
				<h2>Choosing the Right Specialist for Your Condition</h2>
				<p>Confused between a general physician, dermatologist, or
					cardiologist? Here's how to pick the right doctor based on your
					symptoms.</p>
				<a href="#" class="read-more">Keep Reading →</a>
			</div>
		</div>
	</section>

	<%-- Include common footer --%>
	<jsp:include page="footer.jsp" />

</body>
</html>
