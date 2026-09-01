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
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">Sunrise Dental Clinic</a>
            <div class="navbar-nav ml-auto">
                <span class="navbar-text text-white">
                    Welcome, ${sessionScope.username}!
                </span>
                <a href="LogoutServlet" class="btn btn-danger btn-sm ml-3">Logout</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-3">
                <div class="dashboard-card">
                    <div class="icon">📅</div>
                    <h5>Register Appointment</h5>
                    <p>Create a new patient appointment</p>
                    <a href="RegisterAppointmentServlet" class="btn btn-primary btn-sm">Go</a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="dashboard-card">
                    <div class="icon">🔍</div>
                    <h5>Search Appointment</h5>
                    <p>View appointment details</p>
                    <a href="SearchAppointmentServlet" class="btn btn-primary btn-sm">Go</a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="dashboard-card">
                    <div class="icon">💰</div>
                    <h5>Calculate Bill</h5>
                    <p>Generate patient bill</p>
                    <a href="calculateBill.jsp" class="btn btn-primary btn-sm">Go</a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="dashboard-card">
                    <div class="icon">❓</div>
                    <h5>Help</h5>
                    <p>Learn how to use the system</p>
                    <a href="help.jsp" class="btn btn-primary btn-sm">Go</a>
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-3">
                <div class="dashboard-card">
                    <div class="icon">👤</div>
                    <h5>Manage Users</h5>
                    <p>Register new staff members</p>
                    <a href="RegisterUserServlet" class="btn btn-primary btn-sm">Go</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>