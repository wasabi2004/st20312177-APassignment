<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dental Clinic Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="login-page">
    <div class="login-container">
        <div class="login-card">
            <div class="text-center">
                <h4>Sunrise Dental Clinic</h4>
                <p class="subtitle">Login to access the system</p>
            </div>
            
            <%
                String savedUsername = "";
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("savedUsername".equals(cookie.getName())) {
                            savedUsername = cookie.getValue();
                            break;
                        }
                    }
                }
            %>
            
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" name="username" class="form-control" 
                           placeholder="Enter username" value="<%= savedUsername %>" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" 
                           placeholder="Enter password" required>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="rememberMe" id="rememberMe">
                    <label class="form-check-label" for="rememberMe">Remember Me</label>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3">${errorMessage}</div>
            </c:if>
        </div>
    </div>
</body>
</html>