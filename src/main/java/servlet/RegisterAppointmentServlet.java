package servlet;

import dao.AppointmentDAO;
import dao.PatientDAO;
import dao.TreatmentDAO;
import dao.DentistDAO;
import model.Appointment;
import model.Patient;
import model.Treatment;
import model.Dentist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/RegisterAppointmentServlet")
public class RegisterAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load treatments for dropdown
        TreatmentDAO treatmentDAO = new TreatmentDAO();
        List<Treatment> treatments = treatmentDAO.getAllTreatments();
        request.setAttribute("treatments", treatments);

        // Load dentists for dropdown
        DentistDAO dentistDAO = new DentistDAO();
        List<Dentist> dentists = dentistDAO.getAllDentists();
        request.setAttribute("dentists", dentists);

        request.getRequestDispatcher("registerAppointment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        int treatmentId = Integer.parseInt(request.getParameter("treatmentId"));
        int dentistId = Integer.parseInt(request.getParameter("dentistId"));
        String dateTimeStr = request.getParameter("dateTime");
        double consultationFee = Double.parseDouble(request.getParameter("consultationFee"));

        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

        // Get dentist name from ID
        DentistDAO dentistDAO = new DentistDAO();
        Dentist dentist = dentistDAO.getDentistById(dentistId);
        String dentistName = dentist != null ? dentist.getName() : "";

        // Check availability
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        boolean available = appointmentDAO.checkAvailability(dentistName, dateTime);

        if (!available) {
            request.setAttribute("errorMessage", "Dentist is not available at this time.");
            
            // Reload treatments and dentists for the form
            TreatmentDAO treatmentDAO = new TreatmentDAO();
            List<Treatment> treatments = treatmentDAO.getAllTreatments();
            request.setAttribute("treatments", treatments);

            List<Dentist> dentists = dentistDAO.getAllDentists();
            request.setAttribute("dentists", dentists);
            
            request.getRequestDispatcher("registerAppointment.jsp").forward(request, response);
            return;
        }

        // Save patient
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAddress(address);
        patient.setContact(contact);

        PatientDAO patientDAO = new PatientDAO();
        int patientId = patientDAO.savePatient(patient);

        // Save appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setTreatmentId(treatmentId);
        appointment.setDentistName(dentistName);
        appointment.setDateTime(dateTime);
        appointment.setConsultationFee(consultationFee);

        int appointmentId = appointmentDAO.saveAppointment(appointment);

        request.setAttribute("appointmentId", appointmentId);
        request.setAttribute("successMessage", "Appointment registered successfully! Appointment ID: " + appointmentId);
        
        // Reload treatments and dentists for the form
        TreatmentDAO treatmentDAO = new TreatmentDAO();
        List<Treatment> treatments = treatmentDAO.getAllTreatments();
        request.setAttribute("treatments", treatments);

        List<Dentist> dentists = dentistDAO.getAllDentists();
        request.setAttribute("dentists", dentists);
        
        request.getRequestDispatcher("registerAppointment.jsp").forward(request, response);
    }
}