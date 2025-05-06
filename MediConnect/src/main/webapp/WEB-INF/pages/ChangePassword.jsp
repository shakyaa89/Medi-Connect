<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Declare the page language and content encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- Include JSTL core and functions tag libraries --%>

<%-- Create variables for username, role and contextpath --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/css/ChangePassword.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
<title>MediConnect - Admin</title>
</head>
<body>
	<%-- Including header --%>
	<jsp:include page="header.jsp" />
	<%-- Display Main Content if the user is logged in --%>
	<section class="main-content">
		<jsp:include page="leftNavigation.jsp" />

		<div class="content-edit">
			<div class="change-password-box">
				<a href="${currentRole }Dashboard" class="back-link"> ← Go back</a>
				<h2>Change password</h2>
				<form action="ChangePassword" method="post">
					<label>Current Password</label> <input type="password" name="oldpassword"
						id="oldpassword" placeholder="Enter current password" /> <label>New
						Password</label> <input type="password" id="newpassword" name="newpassword"
						placeholder="Enter new password" /> <label>Re-enter New
						Password</label> <input type="password" id="re-newpassword" name="re-newpassword"
						placeholder="Re-enter new password" />

					<button>Change password</button>
				</form>
			</div>
		</div>
	</section>
</body>
</html>