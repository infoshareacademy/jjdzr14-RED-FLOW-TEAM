package pl.infoshare.clinicweb.doctor;

import jakarta.persistence.*;
import lombok.Data;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    private boolean online;
    private boolean availability;
    private List<Patient> patient;
    private PersonDetails details;
    private Address address;

}


