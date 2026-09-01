package model;

public class Treatment {
    private int treatmentId;
    private String typeName;
    private double baseCost;

    public Treatment() {}

    public Treatment(int treatmentId, String typeName, double baseCost) {
        this.treatmentId = treatmentId;
        this.typeName = typeName;
        this.baseCost = baseCost;
    }

    public int getTreatmentId() { return treatmentId; }
    public void setTreatmentId(int treatmentId) { this.treatmentId = treatmentId; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public double getBaseCost() { return baseCost; }
    public void setBaseCost(double baseCost) { this.baseCost = baseCost; }
}