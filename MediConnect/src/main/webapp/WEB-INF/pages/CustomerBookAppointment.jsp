<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/CustomerBookAppointment.css" />
</head>
<body>
	
	<jsp:include page="header.jsp" />
	<section class="main-content">
            <jsp:include page="leftNavigation.jsp" />

        <div class="list-content">
        <div class="card">
          <h2>Book an Appointment</h2>
          <form action="">
            <div class="selectors">
              <div class="input-group">
                <label>Select Date</label>
                <input type="date" name="date" id="date" />
              </div>
              <div class="input-group">
                <label>Select Time</label>
                <input type="time" placeholder="Choose time" required />
              </div>
            </div>
            <div class="selectors">
              <div class="doctor">
                <label>Select Doctor</label>
                <select
                  id="doctors"
                  name="doctors"
                  class="select-field"
                  required
                >
                  <option value="" disabled selected>Select a Doctor</option>
                  <option value="general">General Medicine</option>
                  <option value="pediatrics">Pediatrics</option>
                  <option value="cardiology">Cardiology</option>
                  <option value="dermatology">Dermatology</option>
                </select>
              </div>
            </div>
            <button class="book-btn">Book Appointment</button>
          </form>
        </div>
      </div>
        </section>
    
    
    <jsp:include page="footer.jsp" />
    
</body>
</html>