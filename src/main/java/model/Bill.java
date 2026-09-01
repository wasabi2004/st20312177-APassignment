package model;

import java.time.LocalDate;

public class Bill {
    private int billId;
    private int appointmentId;
    private double totalAmount;
    private LocalDate issuedDate;

    public Bill() {}

    public Bill(int billId, int appointmentId, double totalAmount, LocalDate issuedDate) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.totalAmount = totalAmount;
        this.issuedDate = issuedDate;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }
    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }
}