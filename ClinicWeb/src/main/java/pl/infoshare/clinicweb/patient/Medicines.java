package pl.infoshare.clinicweb.patient;

public class Medicines {

    private String medicineName;
    private int numberOfMedicine;
    private String diagnosis;

    public Medicines(String medicineName, int numberOfMedicine, String diagnosis) {

        this.medicineName = medicineName;
        this.numberOfMedicine = numberOfMedicine;
        this.diagnosis = diagnosis;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getNumberOfMedicine() {
        return numberOfMedicine;
    }

    public void setNumberOfMedicine(int numberOfMedicine) {
        this.numberOfMedicine = numberOfMedicine;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Medicines{" +
                "medicineName='" + medicineName + '\'' +
                ", numberOfMedicine=" + numberOfMedicine +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
