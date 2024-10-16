package pl.infoshare.clinicweb.patient;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.user.PersonDetails;


@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonDetails personDetails;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    private Clinic clinic;

    @Embedded
    private Address address;
}
