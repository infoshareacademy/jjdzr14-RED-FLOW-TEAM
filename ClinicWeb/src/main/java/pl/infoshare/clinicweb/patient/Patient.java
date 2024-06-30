package pl.infoshare.clinicweb.patient;

import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.User;

public class Patient {

    private PersonDetails details;
    private Clinic clinic;
    private Doctor doctor;
    private Address address;

    public Patient () {

    }

    public Patient(PersonDetails details, Address address) {

        this.details = details;
        this.address = address;

    }

    public Patient(PersonDetails details, Address address, Clinic clinic, Doctor doctor) {


        this.details = details;
        this.address = address;
        this.clinic = clinic;
        this.doctor = doctor;

    }

    public PersonDetails getPersonDetails() {
        return details;
    }

    public void setPersonDetails(PersonDetails details) {
        this.details = details;
    }


    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Patient{\n" +
                "details=" + details +
                " , clinic=" + clinic +
                " , doctor=" + doctor +
                " , address=" + address +
                '}';
    }
}
