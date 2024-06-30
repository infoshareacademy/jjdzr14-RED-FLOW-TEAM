package pl.infoshare.clinicweb.patient;

import pl.infoshare.clinicweb.doctor.Doctor;

import java.time.LocalDateTime;

public class Visits {

    private Doctor doctor;
    private LocalDateTime visitDate;

    public Visits(Doctor doctor, LocalDateTime visitDate) {
        this.doctor = doctor;
        this.visitDate = visitDate;
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
