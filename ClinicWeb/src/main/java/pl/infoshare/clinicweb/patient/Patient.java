package pl.infoshare.clinicweb.patient;

import jakarta.persistence.*;
import lombok.Data;
import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.user.PersonDetails;


@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private PersonDetails personDetails;
    @ManyToOne
    private Clinic clinic;
    @ManyToOne
    private Doctor doctor;
    @Embedded
    private Address address;



}
