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
    <title>Search Appointment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        .clickable-row:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
        .table-container {
            max-height: 400px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">Sunrise Dental Clinic</a>
            <a href="LogoutServlet" class="btn btn-danger btn-sm ml-auto">Logout</a>
        </div>
    </nav>

    <div class="container mt-4">
        <h3>Search Appointment Details</h3>

        <div class="row">
            <div class="col-md-6">
                <form action="SearchAppointmentServlet" method="post" class="form-inline">
                    <div class="form-group mr-2">
                        <label class="mr-2">Appointment Number:</label>
                        <input type="number" name="appointmentId" class="form-control" placeholder="Enter ID">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                    <a href="SearchAppointmentServlet" class="btn btn-secondary ml-2">Show All</a>
                </form>
            </div>
            <div class="col-md-6 text-right">
                <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
            </div>
        </div>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3">${errorMessage}</div>
        </c:if>

        <c:if test="${not empty appointment}">
            <div class="card mt-4">
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

        <div class="mt-4">
            <h4>All Appointments</h4>
            <div class="table-container">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Patient Name</th>
                            <th>Treatment</th>
                            <th>Dentist</th>
                            <th>Date & Time</th>
                            <th>Fee</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${appointments}" var="a">
                            <tr>
                                <td>${a.appointmentId}</td>
                                <td>${a.patientName}</td>
                                <td>${a.treatmentName}</td>
                                <td>${a.dentistName}</td>
                                <td>${a.dateTime}</td>
                                <td>Rs. ${a.consultationFee}</td>
                                <td>
                                    <form action="SearchAppointmentServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="appointmentId" value="${a.appointmentId}">
                                        <button type="submit" class="btn btn-sm btn-info">View</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty appointments}">
                            <tr>
                                <td colspan="7" class="text-center">No appointments found.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>