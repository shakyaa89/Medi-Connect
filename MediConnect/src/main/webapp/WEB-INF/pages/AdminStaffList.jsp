<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- Set session and context path variables --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Link CSS files for styling --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/AdminStaffList.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ErrorMessage.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<title>MediConnect - Admin</title>
</head>
<body>

	<%-- Include header section --%>
	<jsp:include page="header.jsp" />

	<section class="main-content">

		<%-- Include left-side navigation --%>
		<jsp:include page="leftNavigation.jsp" />

		<div class="list-content">
			<div class="staff-container">

				<%-- Page heading and search form --%>
				<div class="staff-head">
					<h1>Staff List</h1>
					<form action="">
						<input type="text" placeholder="Search">
					</form>
				</div>

				<%-- Staff list table --%>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Username</th>
							<th>Gender</th>
							<th>Address</th>
							<th>Date of Birth</th>
							<th>Email</th>
							<th>Phone No</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%-- Loop through each staff member and display data --%>
						<c:forEach var="staff" items="${staffList}">
							<tr>
								<td>${staff.user_id}</td>
								<td>${staff.user_first_name}${staff.user_last_name }</td>
								<td>${staff.user_username}</td>
								<td>${staff.user_gender}</td>
								<td>${staff.user_location}</td>
								<td>${staff.user_dob}</td>
								<td>${staff.user_email}</td>
								<td>${staff.user_phonenumber}</td>
								<td class="buttons">
									<%-- Edit staff form --%>
									<form action="AdminEditStaff" method="get">
										<input type="hidden" name="staffId" value="${staff.user_id}" />
										<button class="edit-btn">Edit Staff</button>
									</form> <%-- Delete staff form with confirmation prompt --%>
									<form action="AdminStaffList" method="post"
										onsubmit="return confirm('Are you sure you want to delete this staff?');">
										<input type="hidden" name="staffId" value="${staff.user_id}" />
										<button class="edit-btn">Delete Staff</button>
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
