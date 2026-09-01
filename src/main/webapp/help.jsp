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
    <title>Help</title>
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
        <h3>Help - How to Use the System</h3>

        <div class="card mt-3">
            <div class="card-header bg-info text-white">1. Login</div>
            <div class="card-body">
                Enter your username and password on the login page. Click the Login button to access the system.
            </div>
        </div>

        <div class="card mt-3">
            <div class="card-header bg-info text-white">2. Register New Appointment</div>
            <div class="card-body">
                Fill in all patient details including name, address, and contact number. Select the treatment type, enter dentist name, choose date and time, and enter consultation fee. Click Register Appointment to save.
            </div>
        </div>

        <div class="card mt-3">
            <div class="card-header bg-info text-white">3. Display Appointment Details</div>
            <div class="card-body">
                Enter the appointment number in the search box. Click Search to view complete patient and appointment information.
            </div>
        </div>

        <div class="card mt-3">
            <div class="card-header bg-info text-white">4. Calculate and Print Bill</div>
            <div class="card-body">
                Enter the appointment number. Add a discount percentage if applicable. Click Generate Bill to calculate the total. On the bill page, click Print Receipt to print the bill.
            </div>
        </div>

        <div class="card mt-3">
            <div class="card-header bg-info text-white">5. Exit System</div>
            <div class="card-body">
                Click the Logout button located at the top right of any page to safely exit the system.
            </div>
        </div>

        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>