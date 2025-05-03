<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

  
  
  <!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CustomerList.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ErrorMessage.css"/>
		<link
		      rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
		    />
		<title>MediConnect - Admin</title>
		</head>
		<body>
		        <jsp:include page="header.jsp"/>
		
		<section class="main-content">
		      <jsp:include page="leftNavigation.jsp"/>
		
		      <div class="list-content">
            <div class="patient-container">
                <div class="patient-head">
                    <h1>Customer List</h1>
                    <form action="">
                        <input type="text" placeholder="Search">
                    </form>
                </div>
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
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="customer" items="${customerList}">
		                    <tr>
		                        <td>${customer.user_id}</td>
		                        <td>${customer.user_first_name} ${staff.user_last_name }</td>
		                        <td>${customer.user_username}</td>
		                        <td>${customer.user_gender}</td>
		                        <td>${customer.user_location}</td>
		                        <td>${customer.user_dob}</td>
		                        <td>${customer.user_email}</td>
		                        <td>${customer.user_phonenumber}</td>
		                    </tr>
	                	</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
		      
		    </section>
		</body>
		</html>
  
