<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculate Bill</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">Sunrise Dental Clinic</a>
            <a href="LogoutServlet" class="btn btn-danger btn-sm ml-auto">Logout</a>
        </div>
    </nav>

    <div class="container mt-4">
        <h3>Calculate Bill</h3>
        <form action="BillServlet" method="post">
            <div class="form-group">
                <label>Appointment Number</label>
                <input type="number" name="appointmentId" class="form-control" required>
            </div>
            <div class="form-group">
                <label>Discount Percentage (optional)</label>
                <input type="number" step="0.01" name="discount" class="form-control" value="0">
            </div>
            <button type="submit" class="btn btn-primary">Generate Bill</button>
            <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </form>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3">${errorMessage}</div>
        </c:if>
    </div>
</body>
</html>