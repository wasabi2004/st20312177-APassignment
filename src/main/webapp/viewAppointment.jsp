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
    <title>View Appointment</title>
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
        <h3>Appointment Details</h3>

        <c:if test="${not empty appointment}">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <h5>Appointment #${appointment.appointmentId}</h5>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-6">
                            <h5>Patient Information</h5>
                            <p><strong>Name:</strong> ${patient.name}</p>
                            <p><strong>Address:</strong> ${patient.address}</p>
                            <p><strong>Contact:</strong> ${patient.contact}</p>
                        </div>
                        <div class="col-md-6">
                            <h5>Appointment Information</h5>
                            <p><strong>Dentist:</strong> ${appointment.dentistName}</p>
                            <p><strong>Date & Time:</strong> ${appointment.dateTime}</p>
                            <p><strong>Treatment:</strong> ${treatment.typeName}</p>
                            <p><strong>Consultation Fee:</strong> Rs. ${appointment.consultationFee}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${empty appointment}">
            <div class="alert alert-danger mt-3">Appointment not found.</div>
        </c:if>

        <a href="searchAppointment.jsp" class="btn btn-primary mt-3">Search Another</a>
        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>