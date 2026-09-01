package servlet;

import dao.AppointmentDAO;
import dao.PatientDAO;
import dao.TreatmentDAO;
import model.Appointment;
import model.Patient;
import model.Treatment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchAppointmentServlet")
public class SearchAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        List<Appointment> appointments = appointmentDAO.getAllAppointmentsWithPatientNames();
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("searchAppointment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("appointmentId");

            if (idParam == null || idParam.trim().isEmpty()) {
                doGet(request, response);
                return;
            }

            int appointmentId = Integer.parseInt(idParam);

            AppointmentDAO appointmentDAO = new AppointmentDAO();
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);

            if (appointment == null) {
                request.setAttribute("errorMessage", "Appointment not found.");
                List<Appointment> appointments = appointmentDAO.getAllAppointmentsWithPatientNames();
                request.setAttribute("appointments", appointments);
                request.getRequestDispatcher("searchAppointment.jsp").forward(request, response);
                return;
            }

            PatientDAO patientDAO = new PatientDAO();
            Patient patient = patientDAO.getPatientById(appointment.getPatientId());

            TreatmentDAO treatmentDAO = new TreatmentDAO();
            Treatment treatment = treatmentDAO.getTreatmentById(appointment.getTreatmentId());

            List<Appointment> appointments = appointmentDAO.getAllAppointmentsWithPatientNames();
            request.setAttribute("appointments", appointments);
            request.setAttribute("appointment", appointment);
            request.setAttribute("patient", patient);
            request.setAttribute("treatment", treatment);
            request.getRequestDispatcher("searchAppointment.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Please enter a valid number.");
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            List<Appointment> appointments = appointmentDAO.getAllAppointmentsWithPatientNames();
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("searchAppointment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error searching for appointment: " + e.getMessage());
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            List<Appointment> appointments = appointmentDAO.getAllAppointmentsWithPatientNames();
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("searchAppointment.jsp").forward(request, response);
        }
    }
}