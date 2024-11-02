package pl.infoshare.clinicweb.clinic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;

import java.util.Set;

@Data
@Entity
public class Clinic {

    @Id
    @GeneratedValue
    private long id;
    private String clinicName;
    @OneToMany
    private Set<Patient> patients;
    @OneToMany
    private Set<Doctor> doctors;
    private Address address;

}
