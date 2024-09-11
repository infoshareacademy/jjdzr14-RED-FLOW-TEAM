package pl.infoshare.patientCard;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientCardDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String symptoms;
    private LocalDate dateOfVisit;
    private String noteDoctor;
    private String noteMedicalHistory;
    private String diagnosis;
    private String treatment;

}
