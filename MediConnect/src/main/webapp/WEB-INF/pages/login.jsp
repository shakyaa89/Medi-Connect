<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Variables to get username --%>
<c:set var="userSession" value="${pageContext.session}" />
<c:set var="currentUser" value="${userSession.getAttribute('username')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MediConnect</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
<%-- Including the header --%>
<jsp:include page="header.jsp" />
<%-- Checking if the user is logged in or not --%>
<c:choose>
    <%-- If the user is logged in, show a message and redirect button --%>
    <c:when test="${not empty currentUser}">
        <div class="error-message">
            <h2>You are already logged in!</h2>
            <p><a href="index">Go back to home page!!</a></p>
        </div>
    </c:when>

    <%-- If the user is not logged in, show the Login form --%>
    <c:otherwise>
        <section class="main-container">
            <div>
                <form action="" method="post" class="form-container">
                    <%-- Logo and welcome message --%>
                    <img src="${pageContext.request.contextPath}/images/logoblack.png" alt="Logo" height="50px" />
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

                    <%-- Login button --%>
                    <button class="login-btn-form" type="submit">Login</button>

                    <%-- Link to registration page --%>
                    <p>Don't have an account? <a href="register">Register here</a></p>
                </form>
            </div>
        </section>

        <%-- Including the footer --%>
        <jsp:include page="footer.jsp" />
    </c:otherwise>
</c:choose>

</body>
</html>
