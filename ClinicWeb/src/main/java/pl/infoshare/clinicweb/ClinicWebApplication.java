package pl.infoshare.clinicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.file.GeneratorData;
import pl.infoshare.clinicweb.patient.Patient;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicWebApplication.class, args);
//        GeneratorData generatorData = new GeneratorData();
//        Patient patient = new Patient();
//        generatorData.writeRandomObjects(10, patient);
//        Doctor doctor = new Doctor();
//        generatorData.writeRandomObjects(10, doctor);
    }
}