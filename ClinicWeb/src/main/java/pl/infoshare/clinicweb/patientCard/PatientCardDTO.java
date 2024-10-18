package pl.infoshare.clinicweb.patientCard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientCardDTO {
    private Long id;
    private String patientPesel;
    private String patientFirstName;
    private String patientLastName;
    private String doctorFirstName;
    private String doctorLastName;
    private String symptoms;
    private LocalDateTime dateOfVisit;
    private String noteDoctor;
    private String diagnosis;
    private String treatment;
    private Long patientId;
    private Long doctorId;


}
