package pl.infoshare.clinicweb.doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;

@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue
    private long id;
    private String specialization;
    private List<Patient> patient;
    private boolean online;
    private boolean availability;
    private PersonDetails personDetails;
    private Address address;

}


