<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<%-- Link to About Us page CSS --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AboutUs.css" />
</head>
<body>
	
	<%-- Include common header --%>
	<jsp:include page="header.jsp" />
	
    <section class="about-section">
      <div class="about-content">
        <h2>About Us</h2>
        <p>
          At MediConnect, we are committed to making healthcare more accessible,
          convenient, and efficient for everyone. Our platform connects patients
          with trusted doctors and healthcare providers, offering an easy way to
          schedule appointments, access medical records, and receive care from
          the comfort of their homes. We believe that managing your health
          should be simple and stress-free, which is why we are dedicated to
          providing reliable services backed by modern technology and
          compassionate support. Your well-being is our priority, and we are
          here to help you every step of the way.
        </p>

        <h2>Our Goal</h2>
        <p>
          Our goal is to bridge the gap between patients and healthcare
          professionals by delivering a seamless, user-friendly experience. We
          aim to empower individuals to take control of their health journey by
          providing timely appointments, expert advice, and continuous support.
          Through innovation and care, we strive to improve healthcare access
          and enhance the overall patient experience across all communities.
        </p>
      </div>

      <div class="about-image">
        <%-- Display About Us illustration image --%>
        <img src="${pageContext.request.contextPath}/images/aboutusimage.jpg" alt="About Us Illustration" />
      </div>
    </section>
    
    <%-- Include common footer --%>
    <jsp:include page="footer.jsp" />
    
</body>
</html>
