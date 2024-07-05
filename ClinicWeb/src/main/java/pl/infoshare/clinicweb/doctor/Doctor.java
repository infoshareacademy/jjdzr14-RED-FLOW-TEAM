package pl.infoshare.clinicweb.doctor;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.User;

import java.util.List;


public class Doctor {
    private User user;
    private String specialization;
    private List<Patient> patient;
    private Clinic clinic;
    private boolean online;
    private boolean availability;
    private PersonDetails details;
    private Address address;

    public Doctor() {
    }

    public Doctor(User user, PersonDetails details, Address address, String specialization, boolean online, boolean availability) {

        this.user = user;
        this.details = details;
        this.address = address;
        this.specialization = specialization;
        this.online = online;
        this.availability = availability;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "user=" + user +
                "address=" + address +
                ", specialization='" + specialization +
                ", patient=" + patient +
                ", clinic=" + clinic +
                ", online=" + online +
                ", availability=" + availability +
                ", details=" + details +
                '}';
    }
}
