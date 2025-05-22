<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Retrieve session and context values --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%-- Initialize variables for login/logout and UI control --%>
<c:set var="actionUrl" />
<c:set var="formMethod" />
<c:set var="buttonLabel" />
<c:set var="registerDisplay" />
<c:set var="dashboardHide" />

<%-- Determine UI behavior based on user login status --%>
<c:choose>
	<c:when test="${currentUser != null}">
		<%-- If user is logged in --%>
		<c:set var="actionUrl" value="${contextPath}/logout" />
		<c:set var="formMethod" value="post" />
		<c:set var="buttonLabel" value="Logout" />
		<c:set var="registerDisplay" value="hide" />
	</c:when>
	<c:otherwise>
		<%-- If user is not logged in --%>
		<c:set var="actionUrl" value="${contextPath}/login" />
		<c:set var="formMethod" value="get" />
		<c:set var="buttonLabel" value="Login" />
		<c:set var="registerDisplay" value="button register-btn" />
		<c:set var="dashboardHide" value="hide" />
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%-- Link to header CSS --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>

	<%-- Header section starts --%>
	<header class="header">
		<div class="header-content">

			<%-- Logo area --%>
			<div class="logo">
				<img src="${pageContext.request.contextPath}/images/logowhite.png"
					alt="Company Logo" />
			</div>

			<%-- Navigation and buttons --%>
			<div class="header-element">
				<nav>
					<ul>
						<li><a href="index">Home</a></li>
						<li><a href="Blog">Blog</a></li>
						<li><a href="AboutUs">About Us</a></li>
						<li><a href="ContactUs">Contact Us</a></li>

						<%-- Display dashboard link based on user role --%>
						<c:choose>
							<c:when test="${currentRole == 'Admin'}">
								<li><a href="AdminDashboard" class="${dashboardHide }">${currentUser }</a></li>
							</c:when>

							<c:when test="${currentRole == 'Staff'}">
								<li><a href="StaffDashboard" class="${dashboardHide }">${currentUser }</a></li>
							</c:when>

							<c:otherwise>
								<li><a href="CustomerDashboard" class="${dashboardHide }">${currentUser }</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>

				<%-- Login/Logout button --%>
				<form action="${actionUrl }" method="${formMethod }">
					<button class="button login-btn">${buttonLabel }</button>
				</form>

				<%-- Register button (only shown if user not logged in) --%>
				<button class="${registerDisplay}"
					onclick="window.location.href='register'">Register</button>
			</div>
		</div>
	</header>
</body>
</html>
