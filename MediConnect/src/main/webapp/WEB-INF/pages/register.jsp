<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MediConnect</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>

    <%-- Including header --%>
    <jsp:include page="header.jsp" />

    <div class="register">
        <div class="register-container">
            <div class="register-form">

                <%-- Welcome text and Logo --%>
                <div class="form-header">
                    <img src="${pageContext.request.contextPath}/images/logoblack.png" />
                    <h3>Welcome to Mediconnect</h3>
                </div>

                <%-- Registration form --%>
                <form class="form-section" action="register" method="post">
                    <div>

                        <%-- First row --%>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="firstname">First Name</label>
                                <input type="text" id="firstname" name="firstname" required>
                            </div>
                            <div class="form-column">
                                <label for="lastname">Last Name</label>
                                <input type="text" id="lastname" name="lastname" required>
                            </div>
                        </div>

                        <%-- Second row --%>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="username">Username</label>
                                <input type="text" id="username" name="username" required>
                            </div>
                            <div class="form-column">
                                <label for="location">Location</label>
                                <input type="text" id="location" name="location" required>
                            </div>
                        </div>

                        <%-- Third row --%>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" required>
                            </div>
                            <div class="form-column">
                                <label for="phoneNumber">Phone Number</label>
                                <input type="text" id="phoneNumber" name="phoneNumber" required>
                            </div>
                        </div>

                        <%-- Fourth row --%>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="gender">Gender</label>
                                <select name="gender" id="gender">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Others">Others</option>
                                </select>
                            </div>
                            <div class="form-column">
                                <label for="dateOfBirth">Date of Birth</label>
                                <input type="date" id="dateOfBirth" name="dateOfBirth" required>
                            </div>
                        </div>

                        <%-- Fifth row --%>
                        <div class="form-row">
                            <div class="form-column">
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password" required>
                            </div>
                            <div class="form-column">
                                <label for="retypePassword">Retype Password</label>
                                <input type="password" id="retypePassword" name="retypePassword" required>
                            </div>
                        </div>

                    </div>

                    <%-- Sign-up Button --%>
                    <div class="signup-buttons">
                        <button class="signup" type="submit">Sign up</button>
                        <p>Already have an account? <a href="login">Login</a> here</p>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <%-- Including footer --%>
    <jsp:include page="footer.jsp" />

</body>
</html>
