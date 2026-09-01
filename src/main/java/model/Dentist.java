package model;

public class Dentist {
    private int dentistId;
    private String name;
    private String specialization;
    private String contact;

    public Dentist() {}

    public Dentist(int dentistId, String name, String specialization, String contact) {
        this.dentistId = dentistId;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
    }

    public int getDentistId() { return dentistId; }
    public void setDentistId(int dentistId) { this.dentistId = dentistId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}