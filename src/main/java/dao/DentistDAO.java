package dao;

import model.Dentist;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DentistDAO {

    public Dentist getDentistById(int dentistId) {
        String sql = "SELECT * FROM dentists WHERE dentistId = ?";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dentistId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Dentist(rs.getInt("dentistId"), rs.getString("name"), 
                                   rs.getString("specialization"), rs.getString("contact"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Dentist> getAllDentists() {
        List<Dentist> dentists = new ArrayList<>();
        String sql = "SELECT * FROM dentists ORDER BY name";
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dentists.add(new Dentist(rs.getInt("dentistId"), rs.getString("name"), 
                                         rs.getString("specialization"), rs.getString("contact")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentists;
    }
}