<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AdminDoctorList.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />

<title>MediConnect - Admin</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<section class="main-content">

		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="doctor-container">
				<div class="doctor-head">
					<h1>Doctor List</h1>
					<form action="">
						<input type="text" placeholder="Search">
					</form>
				</div>

				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Gender</th>
							<th>Specification</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="doctor" items="${doctorList}">
							<tr>
								<td>${doctor.doctor_id}</td>
								<td>${doctor.doctorFirstName}${doctor.doctorLastName }</td>
								<td>${doctor.doctorGender}</td>
								<td>${doctor.doctorSpecialization}</td>
								<td class="buttons">
									<form action="AdminEditDoctor" method="get">
										<input type="hidden" value="${doctor.doctor_id }"
											id="doctorId" name="doctorId">
										<button class="form-btn">Edit Doctor</button>
									</form>
									<form action="AdminDoctorList" method="post" onsubmit="return confirm('Are you sure you want to delete this doctor?');">
										<input type="hidden" value="${doctor.doctor_id }"
											id="doctorId" name="doctorId">
										<button class="form-btn">Delete Doctor</button>
									</form>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>
	</section>
</body>
</html>