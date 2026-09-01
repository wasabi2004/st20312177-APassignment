package dao;

import model.Appointment;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public int saveAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patientId, treatmentId, dentistName, dateTime, consultationFee) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, appointment.getPatientId());
            ps.setInt(2, appointment.getTreatmentId());
            ps.setString(3, appointment.getDentistName());
            ps.setString(4, appointment.getDateTime().toString());
            ps.setDouble(5, appointment.getConsultationFee());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Appointment getAppointmentById(int appointmentId) {
        String sql = "SELECT * FROM appointments WHERE appointmentId = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Appointment appt = new Appointment();
                appt.setAppointmentId(rs.getInt("appointmentId"));
                appt.setPatientId(rs.getInt("patientId"));
                appt.setTreatmentId(rs.getInt("treatmentId"));
                appt.setDentistName(rs.getString("dentistName"));
                String dateStr = rs.getString("dateTime");
                if (dateStr != null && !dateStr.isEmpty()) {
                    appt.setDateTime(LocalDateTime.parse(dateStr.replace(" ", "T")));
                }
                appt.setConsultationFee(rs.getDouble("consultationFee"));
                return appt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Appointment> getAllAppointmentsWithPatientNames() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.*, p.name as patientName, t.typeName as treatmentName " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patientId = p.patientId " +
                     "JOIN treatments t ON a.treatmentId = t.treatmentId " +
                     "ORDER BY a.appointmentId DESC";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appt = new Appointment();
                appt.setAppointmentId(rs.getInt("appointmentId"));
                appt.setPatientId(rs.getInt("patientId"));
                appt.setTreatmentId(rs.getInt("treatmentId"));
                appt.setDentistName(rs.getString("dentistName"));
                String dateStr = rs.getString("dateTime");
                if (dateStr != null && !dateStr.isEmpty()) {
                    appt.setDateTime(LocalDateTime.parse(dateStr.replace(" ", "T")));
                }
                appt.setConsultationFee(rs.getDouble("consultationFee"));
                appt.setPatientName(rs.getString("patientName"));
                appt.setTreatmentName(rs.getString("treatmentName"));
                appointments.add(appt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public boolean checkAvailability(String dentistName, LocalDateTime dateTime) {
        String sql = "SELECT * FROM appointments WHERE dentistName = ? AND dateTime = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dentistName);
            ps.setString(2, dateTime.toString());
            ResultSet rs = ps.executeQuery();
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}