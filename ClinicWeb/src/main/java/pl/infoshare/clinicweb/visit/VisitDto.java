package pl.infoshare.clinicweb.visit;

import lombok.Data;
import pl.infoshare.clinicweb.doctor.Specialization;

import java.time.LocalDateTime;

@Data
public class VisitDto {

    private long id;
    private LocalDateTime visitDate;
    private String patientName;
    private String patientSurname;
    private String patientPhoneNumber;
    private String patientPesel;
    private String doctorName;
    private String doctorSurname;
    private Specialization doctorSpecialization;
    private boolean isVisitCancelled;
}
