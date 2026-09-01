package dao;

import model.Bill;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class BillDAO {
    public int saveBill(Bill bill) {
        String sql = "INSERT INTO bills (appointmentId, totalAmount, issuedDate) VALUES (?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, bill.getAppointmentId());
            ps.setDouble(2, bill.getTotalAmount());
            ps.setString(3, bill.getIssuedDate().toString());
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

    public Bill getBillByAppointmentId(int appointmentId) {
        String sql = "SELECT * FROM bills WHERE appointmentId = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bill(rs.getInt("billId"), rs.getInt("appointmentId"), 
                                rs.getDouble("totalAmount"), LocalDate.parse(rs.getString("issuedDate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}