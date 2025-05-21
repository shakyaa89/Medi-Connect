<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- Link to error message CSS --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ErrorMessage.css"/>
<title>Error</title>
</head>
<body>
	<%-- Include header.jsp for common header --%>
	<jsp:include page="header.jsp"/>
	
	<%-- Display access denied error message --%>
	<div class="error-message">
            <h2>Access Denied</h2>
            <p>You do not have permission to access this page.</p>
            <p><a href="index">Go Home</a></p>
	</div>
</body>
</html>
