<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ContactUs.css" />
</head>
<body>
	
	<jsp:include page="header.jsp" />
	
    <div class="contactus-container">
      <div class="contactus-header">
        <h1>Contact Us</h1>
        <p>
          If you have any questions or concerns, feel free to reach out to us
          using the form below:
        </p>
      </div>
      <div class="contactus-form">
        <form action="/submit-contact" method="post">
          <div class="form-row">
            <div class="form-column">
              <label for="firstName">First Name</label>
              <input type="text" id="firstName" name="firstName" required />
            </div>
            <div class="form-column">
              <label for="lastName">Last Name</label>
              <input type="text" id="lastNAme" name="lastName" required />
            </div>
          </div>
          <div class="form-row">
            <div class="form-column">
              <label for="email">Email</label>
              <input type="email" id="email" name="email" required />
            </div>
            <div class="form-column">
              <label for="phoneNumber">Number</label>
              <input
                type="number"
                id="phoneNumber"
                name="phoneNumber"
                required
              />
            </div>
          </div>
          <div class="form-row">
            <div class="form-column">
              <label for="message">Message:</label><br />
              <textarea
                class="message-box"
                id="message"
                name="message"
                rows="5"
                required
              ></textarea
              ><br /><br />
            </div>
          </div>
          <div class="buttons">
            <button type="submit">Submit</button>
          </div>
        </form>
      </div>
    </div>
    
    <jsp:include page="footer.jsp" />
    
</body>
</html>