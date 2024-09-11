package pl.infoshare.patientCard;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDate;

@Data
@Entity
@Transactional
public class PatientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    private String symptoms;
    private LocalDate dateOfVisit;
    private String noteDoctor;
    private String noteMedicalHistory;
    private String diagnosis;
    private String treatment;


}
