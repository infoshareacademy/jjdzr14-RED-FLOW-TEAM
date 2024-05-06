package pl.infoshare.model;

public class Clinic {
    String clinicName;
    Address address;
    Patient patient;
    Doctor doctor;

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
