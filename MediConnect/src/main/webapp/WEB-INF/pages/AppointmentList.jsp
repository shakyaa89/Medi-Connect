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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/AppointmentList.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ErrorMessage.css"/>
<title>MediConnect - Admin</title>
</head>
<body>
<jsp:include page="header.jsp"/>

	<section class="main-content">
	
      <jsp:include page="leftNavigation.jsp"/>

      <div class="list-content">
            <div class="appointments-container">
                <div class="appointments-head">
                    <h1>Appointments List</h1>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>Appointment ID</th>
                            <th>Patient ID</th>
                            <th>Patient Name</th>
                            <th>Doctor Appointed</th>
                            <th>Appointment Time</th>
                            <th>Appointment Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                        <tr>
                            <td>001</td>
                            <td>002</td>
                            <td>abcd abcdefgh</td>
                            <td>xyz xyzlmno</td>
                            <td>2:00 AM</td>
                            <td>2025/04/16</td>
                        </tr>
                    </tbody>
                </table>
            </div> 
        </div>
    </section>

</body>
</html>