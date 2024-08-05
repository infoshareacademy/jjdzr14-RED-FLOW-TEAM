package pl.infoshare.clinicweb.doctor;

import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;

public class Doctor {

    private String specialization;
    private List<Patient> patient;
    private boolean online;
    private boolean availability;
    private PersonDetails details;
    private Address address;

    public Doctor() {
    }

    public Doctor(String specialization, List<Patient> patient, boolean online, boolean availability, PersonDetails details, Address address) {
        this.specialization = specialization;
        this.patient = patient;
        this.online = online;
        this.availability = availability;
        this.details = details;
        this.address = address;


    }

    public PersonDetails getPersonDetails() {
        return details;
    }

    public void setPersonDetails(PersonDetails details) {
        this.details = details;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization='" + specialization + '\'' +
                ", patient=" + patient +
                ", online=" + online +
                ", availability=" + availability +
                ", details=" + details +
                ", address=" + address +
                '}';
    }
}


