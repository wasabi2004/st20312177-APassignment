package model;

public class Patient {
    private int patientId;
    private String name;
    private String address;
    private String contact;

    public Patient() {}

    public Patient(int patientId, String name, String address, String contact) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}