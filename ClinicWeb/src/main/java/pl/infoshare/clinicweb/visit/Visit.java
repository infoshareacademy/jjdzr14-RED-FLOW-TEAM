package pl.infoshare.clinicweb.visit;

import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.medicines.Medicines;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Visit {

    private Doctor doctor;
    private Patient patient;
    private List<Medicines> medicines;
    private int numberOfVisits;
    private LocalDateTime visitDate;


    public Visit() {
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
                "doctor=" + doctor +
                ", patient=" + patient +
                ", medicines=" + medicines +
                ", numberOfVisits=" + numberOfVisits +
                ", visitDate=" + visitDate +
                '}';
    }
}
