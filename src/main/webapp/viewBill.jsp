<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Bill</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">Dental Clinic</a>
            <a href="LogoutServlet" class="btn btn-danger btn-sm ml-auto">Logout</a>
        </div>
    </nav>

    <div class="container mt-4">
        <h3>Patient Bill</h3>

        <div class="card">
            <div class="card-header bg-success text-white">
                <h5>Receipt</h5>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <h5>Dental Clinic</h5>
                        <hr>
                        <p><strong>Bill ID:</strong> ${billId}</p>
                        <p><strong>Appointment ID:</strong> ${appointmentId}</p>
                        <p><strong>Patient Name:</strong> ${patientName}</p>
                        <p><strong>Treatment:</strong> ${treatmentName}</p>
                        <hr>
                        <h4>Total Amount: Rs. ${totalAmount}</h4>
                        <hr>
                        <p><strong>Date:</strong> <%= java.time.LocalDate.now() %></p>
                        <button onclick="window.print()" class="btn btn-primary">Print Receipt</button>
                    </div>
                </div>
            </div>
        </div>

        <a href="calculateBill.jsp" class="btn btn-primary mt-3">Calculate Another</a>
        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>