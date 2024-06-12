package pl.infoshare.clinicweb.clinic;

import pl.infoshare.clinicweb.adress.Address;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;

import java.util.Set;

public class Clinic {
    private String clinicName;
    private Address address;
    private Set<Patient> patients;
    private Set<Doctor> doctors ;



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

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Clinic(Address address, String clinicName) {
        this.address = address;
        this.clinicName = clinicName;
    }
}
