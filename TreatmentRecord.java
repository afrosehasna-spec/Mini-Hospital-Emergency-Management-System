/**
 * Represents a completed treatment record, pushed onto the Treatment History stack.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String medicalCondition;
    private String treatmentDate;

    public TreatmentRecord(int patientId, String patientName, String medicalCondition, String treatmentDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.medicalCondition = medicalCondition;
        this.treatmentDate = treatmentDate;
    }

    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getMedicalCondition() { return medicalCondition; }
    public String getTreatmentDate() { return treatmentDate; }

    @Override
    public String toString() {
        return String.format("Patient ID: %-6d | Name: %-20s | Condition Treated: %-15s | Date: %s",
                patientId, patientName, medicalCondition, treatmentDate);
    }
}
