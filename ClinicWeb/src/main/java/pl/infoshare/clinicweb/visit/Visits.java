package pl.infoshare.clinicweb.visit;

import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.format.annotation.DateTimeFormat;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDateTime;

public class Visits {

    private Doctor doctor;
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime visitDate;
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Visits(Doctor doctor, LocalDateTime visitDate) {
        this.doctor = doctor;
        this.visitDate = visitDate;
    }

    public Visits() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Visits{" +
                "doctor=" + doctor +
                ", visitDate=" + visitDate +
                '}';
    }
}
