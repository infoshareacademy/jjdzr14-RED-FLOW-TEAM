package pl.infoshare.clinicweb.visit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.medicines.Medicines;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Visit {
    @NotNull(message = "Pole data nie moze byc puste. ")
    @FutureOrPresent(message = "Wybierz datę z przyszłości. ")
    @JsonSerialize(using = LocalDateTimeSerializer.class)

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime visitDate;

    @Valid
    private Patient patient;
    private DoctorDto doctor;

    private List<Medicines> medicines;
    private int numberOfVisits;


    public Visit() {
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public Patient getPatient() {

        return patient;
    }

    public void setPatient(Patient patient) {

        this.patient = patient;
    }

    public List<Medicines> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicines> medicines) {
        this.medicines = medicines;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {

        this.visitDate =   LocalDateTime.of(visitDate.getYear(),
                visitDate.getMonth(),
                visitDate.getDayOfMonth(),
                visitDate.getHour(),
                visitDate.getMinute(), 0);

    }

    public void setDoctor(DoctorDto doctor) {
        this.doctor = doctor;


    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return numberOfVisits == visit.numberOfVisits && Objects.equals(patient, visit.patient) && Objects.equals(doctor, visit.doctor) && Objects.equals(medicines, visit.medicines) && Objects.equals(visitDate, visit.visitDate);
    }


    @Override
    public int hashCode() {
        return Objects.hash(patient, doctor, medicines, numberOfVisits, visitDate);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", medicines=" + medicines +
                ", numberOfVisits=" + numberOfVisits +
                ", visitDate=" + visitDate +
                '}';
    }
}
