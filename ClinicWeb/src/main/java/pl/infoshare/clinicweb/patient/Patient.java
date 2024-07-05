package pl.infoshare.clinicweb.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.user.PersonDetails;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private PersonDetails personDetails;
    private Clinic clinic;
    private Doctor doctor;
    private Address address;

    public Patient(PersonDetails patientDetails, Address patientAddress) {
        this.personDetails = patientDetails;
        this.address = patientAddress;
    }


    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
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
        return "Patient{" +
                "ID=" + ID +
                ", personDetails=" + personDetails +
                ", clinic=" + clinic +
                ", doctor=" + doctor +
                ", address=" + address +
                '}';
    }
}
