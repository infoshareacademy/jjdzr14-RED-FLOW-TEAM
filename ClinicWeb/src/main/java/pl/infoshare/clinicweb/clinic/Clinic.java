package pl.infoshare.clinicweb.clinic;

import jakarta.persistence.*;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.Internal;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;

import javax.lang.model.type.IntersectionType;
import java.util.Set;

@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Clinic {

    @Id
    @GeneratedValue
    private long id;

    private String clinicName;
    private Set<Patient> patients;
    private Set<Doctor> doctors;
    private Address address;

}
