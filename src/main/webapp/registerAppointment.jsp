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
    <title>Register Appointment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">🦷 Dental Clinic</a>
            <a href="LogoutServlet" class="btn btn-danger btn-sm ml-auto">Logout</a>
        </div>
    </nav>

    <div class="container mt-4">
        <h3>Register New Appointment</h3>
        <form action="RegisterAppointmentServlet" method="post">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Patient Name</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="address" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Contact Number</label>
                        <input type="text" name="contact" class="form-control" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Treatment Type</label>
                        <select name="treatmentId" class="form-control" required style="width:100%; min-width:280px; padding:10px;">
                            <option value="">-- Select Treatment --</option>
                            <c:forEach items="${treatments}" var="t">
                                <option value="${t.treatmentId}" style="padding:8px 15px; white-space:normal; word-wrap:break-word;">
                                    ${t.typeName} - Rs. ${t.baseCost}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Dentist Name</label>
                        <select name="dentistId" class="form-control" required>
                            <option value="">-- Select Dentist --</option>
                            <c:forEach items="${dentists}" var="d">
                                <option value="${d.dentistId}">${d.name} - ${d.specialization}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Date and Time</label>
                        <input type="datetime-local" name="dateTime" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Consultation Fee</label>
                        <input type="number" step="0.01" name="consultationFee" class="form-control" required>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Register Appointment</button>
            <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </form>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success mt-3">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3">${errorMessage}</div>
        </c:if>
    </div>
</body>
</html>