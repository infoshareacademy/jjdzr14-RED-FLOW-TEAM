package pl.infoshare.clinicweb.patientCard;

import jakarta.persistence.*;
import lombok.Data;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patients_cards")
public class PatientCard {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    private String symptoms;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfVisit;
    private String noteDoctor;
    private String diagnosis;
    private String treatment;

}
