package pl.infoshare.clinicweb.visit;

import lombok.Data;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.patient.PatientDto;

import java.time.LocalDateTime;

@Data
public class VisitDto {

    private long id;
    private LocalDateTime visitDate;
    private PatientDto patient;
    private DoctorDto doctor;
    private boolean isVisitCancelled;
}
