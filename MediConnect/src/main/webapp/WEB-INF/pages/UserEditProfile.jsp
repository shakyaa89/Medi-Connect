<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Declare the page language and content encoding --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- Include JSTL core and functions tag libraries --%>

<%-- Create variables for username, role and contextpath --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="currentRole" value="${userSession.getAttribute('role')}" />
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
<%-- Checking if user is logged in or not --%>
<c:choose>
    <c:when test="${empty currentUser}">
        <%-- Error message if user is not logged in --%>
        <div class="error-message">
            <h2>Access Denied</h2>
            <p>You do not have permission to access this page.</p>
            <p><a href="login">You are not logged in!!</a></p>
        </div>
    </c:when>

    <c:otherwise>
    <%-- Display Main Content if the user is logged in --%>
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
                        <form action="" method="post">
                            <%-- Top row --%>
                            <div class="form-row">
                                <div class="form-column">
                                    <label for="firstName">First Name</label>
                                    <input type="text" id="firstName" name="firstName" required>
                                </div>
                                <div class="form-column">
                                    <label for="lastName">Last Name</label>
                                    <input type="text" id="lastName" name="lastName" required>
                                </div>
                                <div class="form-column">
                                    <label for="username">Username</label>
                                    <input type="text" id="username" name="username" required>
                                </div>
                                <div class="form-column">
                                    <label for="gender">Gender</label>
                                    <select name="gender" id="gender">
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="others">Others</option>
                                    </select>
                                </div>
                            </div>

                            <%-- Bottom row --%>
                            <div class="form-row">
                                <div class="form-column">
                                    <label for="location">Location</label>
                                    <input type="text" id="location" name="location" required>
                                </div>
                                <div class="form-column">
                                    <label for="date-of-birth">Date of Birth</label>
                                    <input type="date" id="date-of-birth" name="date-of-birth" required>
                                </div>
                                <div class="form-column">
                                    <label for="email">Email</label>
                                    <input type="email" id="email" name="email" required>
                                </div>
                                <div class="form-column">
                                    <label for="phoneNumber">Phone Number</label>
                                    <input type="tel" id="phoneNumber" name="phoneNumber" required>
                                </div>
                            </div>

                            <%-- Clear and Submit buttons --%>
                            <div class="form-row">
                                <div class="buttons">
                                    <button class="form-buttons" type="reset">Clear</button>
                                    <button class="form-buttons" type="submit">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </c:otherwise>
</c:choose>

</body>
</html>