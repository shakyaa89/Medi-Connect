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
    <link rel="stylesheet" href="${contextPath}/css/ChangePassword.css" />
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

            <div class="content-edit">
        <div class="change-password-box">
          <a href="#" class="back-link"> ← Go back</a>
          <h2>Change password</h2>
          <form>
            <label>Current Password</label>
            <input type="password" id="oldpassword" placeholder="Enter current password" />

            <label>New Password</label>
            <input type="password" id="newPassword" placeholder="Enter new password" />

            <label>Re-enter New Password</label>
            <input type="password" id="re-newPassword" placeholder="Re-enter new password" />

            <button type="submit">Change password</button>
          </form>
        </div>
      </div>
        </section>
    </c:otherwise>
</c:choose>

</body>
</html>