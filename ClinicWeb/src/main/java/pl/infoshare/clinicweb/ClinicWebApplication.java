package pl.infoshare.clinicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

        Doctor doctor = new Doctor();
        doctor.setPersonDetails(new PersonDetails());
        Patient patient = new Patient();

        SpringApplication.run(ClinicWebApplication.class, args);
    }
}
