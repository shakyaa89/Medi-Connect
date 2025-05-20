<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Declare the page language and content encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- Include JSTL core and functions tag libraries --%>

<%-- Create variables for username, role and contextpath --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
<c:set var="firstName" value="${userSession.getAttribute('firstName')}" />
<c:set var="lastName" value="${userSession.getAttribute('lastName')}" />
<c:set var="gender" value="${userSession.getAttribute('gender')}" />
<c:set var="DOB" value="${userSession.getAttribute('DOB')}" />
<c:set var="location" value="${userSession.getAttribute('location')}" />
<c:set var="phoneNumber" value="${userSession.getAttribute('phoneNumber')}" />
<c:set var="email" value="${userSession.getAttribute('email')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${contextPath}/css/UserEditProfile.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ErrorMessage.css"/>
    <title>MediConnect - Admin</title>
</head>
<body>
<%-- Including header --%>
<jsp:include page="header.jsp" />
        <section class="main-content">
            <jsp:include page="leftNavigation.jsp" />

            <div class="list-content">
                <div class="add-staff-container">
                    <div class="add-staff-head">
                        <h1>Edit Profile</h1>
                        <h3>Personal Details</h3>
                    </div>

                    <%-- Form for editing user profile --%>
                    <div class="add-staff-form-container">
                        <form action="" method="post" id="editProfileForm">
                            <%-- Top row --%>
                            <div class="form-row">
                                <div class="form-column">
                                    <label for="firstName">First Name</label>
                                    <input type="text" id="firstName" name="firstName" value="" required>
                                </div>
                                <div class="form-column">
                                    <label for="lastName">Last Name</label>
                                    <input type="text" id="lastName" name="lastName" value="" required>
                                </div>
                                <div class="form-column">
                                    <label for="username">Username</label>
                                    <input type="text" id="username" name="username" value="" required>
                                </div>
                                <div class="form-column">
                                    <label for="gender">Gender</label>
                                    <select name="gender" id="gender">
                                   	 	<option value="${gender }" selected hidden>${gender }</option>
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Others">Others</option>
                                    </select>
                                </div>
                            </div>

                            <%-- Bottom row --%>
                            <div class="form-row">
                                <div class="form-column">
                                    <label for="location">Location</label>
                                    <input type="text" id="location" name="location" value="" required>
                                </div>
                                <div class="form-column">
                                    <label for="date-of-birth">Date of Birth</label>
                                    <input type="date" id="date-of-birth" name="date-of-birth" value="" required>
                                </div>
                                <div class="form-column">
                                    <label for="email">Email</label>
                                    <input type="email" id="email" name="email" value="" required>
                                </div>
                                <div class="form-column">
                                    <label for="phoneNumber">Phone Number</label>
                                    <input type="tel" id="phoneNumber" name="phoneNumber" value="" required>
                                </div>
                            </div>

                            <%-- Clear and Submit buttons --%>
                            <div class="form-row">
                                <div class="buttons">
                                    <button class="form-buttons" type="button" onclick="clearProfileFields()">Clear</button>
                                    <button class="form-buttons" type="submit">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
<script src="${pageContext.request.contextPath}/js/leftNavAdmin.js"></script>
<script>
    window.addEventListener("DOMContentLoaded", function () {
        document.getElementById("firstName").value = "${firstName}";
        document.getElementById("lastName").value = "${lastName}";
        document.getElementById("username").value = "${currentUser}";
        document.getElementById("gender").value = "${gender}";
        document.getElementById("location").value = "${location}";
        document.getElementById("date-of-birth").value = "${DOB}";
        document.getElementById("email").value = "${email}";
        document.getElementById("phoneNumber").value = "${phoneNumber}";
    });
</script>
</body>
</html>