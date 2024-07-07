package pl.infoshare.clinicweb.visit;

import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.medicines.Medicines;
import pl.infoshare.clinicweb.patient.Patient;
import java.util.List;
import java.util.Objects;

public class Visit {
   private Doctor doctor;
    private Patient patient;
   private List<Visit>visits;
   private List<Medicines>medicines;
   private int numberOfVisits;


   public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public Visit(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
        boolean insured = false;
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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Medicines> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicines> medicines) {
        this.medicines = medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(doctor, visit.doctor) && Objects.equals(patient, visit.patient) && Objects.equals(visits, visit.visits) && Objects.equals(medicines, visit.medicines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, visits, medicines);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", visits=" + visits +
                ", medicines=" + medicines +
                ", numberOfVisits=" + numberOfVisits +
                '}';
    }
}
