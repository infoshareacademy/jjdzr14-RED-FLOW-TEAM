package pl.infoshare.clinicweb.visit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import pl.infoshare.clinicweb.medicines.Medicines;
import pl.infoshare.clinicweb.patient.PatientDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Visit {

    @Valid
    private PatientDto patient;

    private List<Medicines> medicines;
    private int numberOfVisits;

    @NotNull(message = "Pole data nie moze byc puste. ")
    @FutureOrPresent(message = "Wybierz datę z przyszłości. ")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime visitDate;


    public Visit(PatientDto patient) {
        this.patient = patient;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
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
        this.visitDate = visitDate;

    }

    public Visit() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Visit visit = (Visit) object;
        return numberOfVisits == visit.numberOfVisits && Objects.equals(patient, visit.patient) && Objects.equals(medicines, visit.medicines) && Objects.equals(visitDate, visit.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, medicines, numberOfVisits, visitDate);
    }


    @Override
    public String toString() {
        return "Visit{" +
                ", patient=" + patient +
                ", medicines=" + medicines +
                ", numberOfVisits=" + numberOfVisits +
                ", visitDate=" + visitDate +
                '}';
    }
}
