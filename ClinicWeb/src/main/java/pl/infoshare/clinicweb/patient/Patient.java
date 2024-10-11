package pl.infoshare.clinicweb.patient;

import jakarta.persistence.*;
import lombok.Data;
import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patientCard.PatientCard;
import pl.infoshare.clinicweb.user.PersonDetails;


@Data
@Entity
@Table(name = "patients")
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonDetails personDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    private PatientCard patientCard;

    @ManyToOne
    private Clinic clinic;

    @Embedded
    private Address address;
}
