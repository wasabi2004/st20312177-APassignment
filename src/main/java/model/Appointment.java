package model;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int treatmentId;
    private String dentistName;
    private LocalDateTime dateTime;
    private double consultationFee;
    private String patientName;
    private String treatmentName;

    public Appointment() {}

    public Appointment(int appointmentId, int patientId, int treatmentId, 
                       String dentistName, LocalDateTime dateTime, double consultationFee) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.treatmentId = treatmentId;
        this.dentistName = dentistName;
        this.dateTime = dateTime;
        this.consultationFee = consultationFee;
    }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public int getTreatmentId() { return treatmentId; }
    public void setTreatmentId(int treatmentId) { this.treatmentId = treatmentId; }
    public String getDentistName() { return dentistName; }
    public void setDentistName(String dentistName) { this.dentistName = dentistName; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getTreatmentName() { return treatmentName; }
    public void setTreatmentName(String treatmentName) { this.treatmentName = treatmentName; }
}