package pl.infoshare.model;

public class Clinic {
    private String clinicName;
    private Address address;
    private Patient patient;
    private Doctor doctor;

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic(Address address, String clinicName) {
        this.address = address;
        this.clinicName = clinicName;
    }
}
