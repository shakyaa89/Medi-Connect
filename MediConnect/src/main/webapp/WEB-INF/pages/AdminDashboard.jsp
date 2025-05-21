<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Get the session object --%>
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
<%-- Link to admin dashboard stylesheet --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admindashboard.css" />
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

		<div class="right-content">
			<div class="top-div">
				<div class="left">
					<h1>Welcome to Admin Dashboard</h1>
					<p>Here you can manage the system.</p>
				</div>
				<div class="right">
					<%-- Dashboard illustration image --%>
					<img
						src="${pageContext.request.contextPath}/images/dashboardimg.jpg"
						alt="" width="200px" />
				</div>
			</div>

			<div class="mid-div">
				<%-- Display total appointments count --%>
				<div class="info-div appointment">
					<i class="fa-solid fa-bookmark"></i>
					<h4>Appointment</h4>
					<p>${dashboardNumbers.numOfAppointment }</p>
				</div>
				<%-- Display total staff count --%>
				<div class="info-div staff">
					<i class="fa-solid fa-user-nurse"></i>
					<h4>Staff</h4>
					<p>${dashboardNumbers.numOfStaff }</p>
				</div>
				<%-- Display total doctors count --%>
				<div class="info-div doctor">
					<i class="fa-solid fa-user-doctor"></i>
					<h4>Doctors</h4>
					<p>${dashboardNumbers.numOfDoctor }</p>
				</div>
				<%-- Display total patients count --%>
				<div class="info-div patients">
					<i class="fa-solid fa-hospital-user"></i>
					<h4>Patients</h4>
					<p>${dashboardNumbers.numOfCustomer }</p>
				</div>
			</div>
			<div class="bottom-div">
				<div class="info-div-btm">
					<h4>Patients by Gender</h4>

					<div class="male-female">
						<%-- Display male patients count --%>
						<div class="gender-div male">
							<i class="fa-solid fa-person"></i>
							<h4>Male</h4>
							<p>${dashboardNumbers.numOfMaleCustomer }</p>
						</div>
						<%-- Display female patients count --%>
						<div class="gender-div female">
							<i class="fa-solid fa-person-dress"></i>
							<h4>Female</h4>
							<p>${dashboardNumbers.numOfFemaleCustomer }</p>
						</div>
					</div>
				</div>
				<div class="info-div-btm">
					<h4>Staff by Gender</h4>

					<div class="male-female">
						<%-- Display male staff count --%>
						<div class="gender-div male">
							<i class="fa-solid fa-person"></i>
							<h4>Male</h4>
							<p>${dashboardNumbers.numOfMaleStaff }</p>
						</div>
						<%-- Display female staff count --%>
						<div class="gender-div female">
							<i class="fa-solid fa-person-dress"></i>
							<h4>Female</h4>
							<p>${dashboardNumbers.numOfFemaleStaff }</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
