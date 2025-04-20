<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CustomerDoctorList.css"/>
<title>MediConnect</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<section class="main-content">
      <jsp:include page="leftNavigation.jsp"/>

      <div class="list-content">
        <div class="doctor-container">
          <div class="doctor-head">
            <h1>Doctor List</h1>
            <form action="">
              <input type="text" placeholder="Search" />
            </form>
          </div>

          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Doctor Name</th>
                <th>Email</th>
                <th>Phone No</th>
                <th>Location</th>
                <th>Specialization</th>
                <th>Gender</th>
                <th>Experience</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
              <tr>
                <td>001</td>
                <td>Dr. Sarah Ali</td>
                <td>sarah.ali@example.com</td>
                <td>+1 555-1234</td>
                <td>New York</td>
                <td>Cardiologist</td>
                <td>Female</td>
                <td>10 years</td>
                <td>09:00 AM</td>
                <td>05:00 PM</td>
                <td><button class="book-btn">Book</button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </section>
    
</body>
</html>