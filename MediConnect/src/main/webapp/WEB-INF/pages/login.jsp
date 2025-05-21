<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Variables to get username --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
</head>
<body>
	<%-- Including the header --%>
	<jsp:include page="header.jsp" />
	<section class="main-container">
		<div>
			<form action="" method="post" class="form-container">
				<%-- Logo and welcome message --%>
				<img src="${pageContext.request.contextPath}/images/logoblack.png"
					alt="Logo" height="50px" />
				<h1>Welcome to MediConnect</h1>
				<%-- Username input --%>
				<div class="label-div">
					<label for="username">Username</label>
				</div>
				<input type="text" name="username" id="username" required />

				<%-- Password input --%>
				<div class="label-div">
					<label for="password">Password</label>
				</div>
				<input type="password" name="password" id="password" required />

				<c:if test="${not empty error}">
					<p style="text-align: center; color: red;">${error}</p>
				</c:if>

				<%-- Login button --%>
				<button class="login-btn-form" type="submit">Login</button>

				<%-- Link to registration page --%>
				<p>
					Don't have an account? <a href="register">Register here</a>
				</p>
			</form>
		</div>
	</section>

	<%-- Including the footer --%>
	<jsp:include page="footer.jsp" />
</body>
</html>
