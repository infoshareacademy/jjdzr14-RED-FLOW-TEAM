package pl.infoshare.clinicweb.visit;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Medicines;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Pole data nie moze byc puste. ")
    @FutureOrPresent(message = "Wybierz datę z przyszłości. ")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime visitDate;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @Embedded
    private List<Medicines> medicines;
    private UUID numberOfVisits = UUID.randomUUID();
    private boolean cancelVisit;

    public void setVisitDate(LocalDateTime visitDate) {

        this.visitDate =   LocalDateTime.of(visitDate.getYear(),
                visitDate.getMonth(),
                visitDate.getDayOfMonth(),
                visitDate.getHour(),
                visitDate.getMinute(), 0);

    }



}
