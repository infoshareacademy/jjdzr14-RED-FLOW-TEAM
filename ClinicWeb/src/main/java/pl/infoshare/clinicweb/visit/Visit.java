package pl.infoshare.clinicweb.visit;

import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.medicines.Medicines;
import pl.infoshare.clinicweb.patient.PatientDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Visit {

    private DoctorDto doctor;
    private PatientDto patient;
    private List<Medicines> medicines;
    private int numberOfVisits;
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm")
    private LocalDateTime visitDate;


    public Visit() {
    }

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Visit visit = (Visit) object;
        return numberOfVisits == visit.numberOfVisits && Objects.equals(doctor, visit.doctor) && Objects.equals(patient, visit.patient) && Objects.equals(medicines, visit.medicines) && Objects.equals(visitDate, visit.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, medicines, numberOfVisits, visitDate);
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
