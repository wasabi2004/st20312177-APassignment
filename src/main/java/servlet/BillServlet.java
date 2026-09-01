package servlet;

import calculator.BillCalculator;
import dao.AppointmentDAO;
import dao.BillDAO;
import dao.TreatmentDAO;
import model.Appointment;
import model.Bill;
import model.Treatment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        AppointmentDAO appointmentDAO = new AppointmentDAO();
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);

        if (appointment == null) {
            request.setAttribute("errorMessage", "Appointment not found.");
            request.getRequestDispatcher("calculateBill.jsp").forward(request, response);
            return;
        }

        TreatmentDAO treatmentDAO = new TreatmentDAO();
        Treatment treatment = treatmentDAO.getTreatmentById(appointment.getTreatmentId());

        double total = BillCalculator.calculateTotal(treatment.getBaseCost(), appointment.getConsultationFee());

        if (discount > 0) {
            total = BillCalculator.applyDiscount(total, discount);
        }

        Bill bill = new Bill();
        bill.setAppointmentId(appointmentId);
        bill.setTotalAmount(total);
        bill.setIssuedDate(LocalDate.now());

        BillDAO billDAO = new BillDAO();
        int billId = billDAO.saveBill(bill);

        request.setAttribute("billId", billId);
        request.setAttribute("totalAmount", total);
        request.setAttribute("patientName", "Patient");
        request.setAttribute("treatmentName", treatment.getTypeName());
        request.setAttribute("appointmentId", appointmentId);
        request.getRequestDispatcher("viewBill.jsp").forward(request, response);
    }
}