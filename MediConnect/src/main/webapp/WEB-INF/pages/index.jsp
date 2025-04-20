<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MediConnect</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>
	
	<jsp:include page="header.jsp" />
	
    <section class="hero">
        <div class="hero-content">
            <div class="hero-text">
                <h1>Your Health, <br> Your Schedule, <br> Our Priority</h1>
                <button class="appointment-btn">Book an Appointment</button>
            </div>
        </div>
    </section>
    
    <section class="doctors-section">
        <h2>Our Doctors</h2>
        <div class="underline"></div>
        <div class="doctors-grid">
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="${pageContext.request.contextPath}/image/home.jpg" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Sarah Johnson</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Michael Chen</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Emily Smith</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Robert Wilson</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Amanda Lee</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. David Patel</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Jessica Kim</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Thomas Brown</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
            
            <div class="doctor-card">
                <div class="doctor-image">
                    <img src="./doctor.png" alt="Doctor profile">
                </div>
                <div class="doctor-info">
                    <h3>Dr. Olivia Martinez</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor, lectus in vestibulum maximus, arcu augue consequat libero, nec aliquam velit.</p>
                </div>
            </div>
        </div>
    </section>
    <section class="healthcare-section">
        <div class="content-container">
            <div class="section-content">
                <h2 class="section-title">Your Trusted Partner in Health</h2>
                <p class="section-lead">Comprehensive Care for Every Stage of Life. At MedConnect</p>
                <p class="section-text">
                    We understand that health needs evolve with time. Our mission is to provide tailored, compassionate care for individuals and families, no matter where they are on life’s journey.</p>
                <div class="healthcare-section-buttons">
                    <button class="appointment-btn">Book an Appointment</button>
                    <button class="appointment-btn">Contact us</button>
                </div>
            </div>
            <div class="healthcare-section-image">
                <img src="./photo1.jpg" alt="">
            </div>
        </div>
    </section>
    
    <jsp:include page="footer.jsp" />
    
</body>
</html>