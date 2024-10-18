package pl.infoshare.clinicweb.patientCard;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOfVisit;
    private String noteDoctor;
    private String diagnosis;
    private String treatment;
    private Long patientId;
    private Long doctorId;


}
