package pl.infoshare.clinicweb.visit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.medicines.Medicines;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Visit {

    private long id;
    @NotNull(message = "Pole data nie moze byc puste. ")
    @FutureOrPresent(message = "Wybierz datę z przyszłości. ")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime visitDate;
    @Valid
    private Patient patient;
    private Doctor doctor;
    private List<Medicines> medicines;
    private UUID numberOfVisits = UUID.randomUUID();
    private boolean cancelVisit;

}
