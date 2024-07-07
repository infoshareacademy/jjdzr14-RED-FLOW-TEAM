package pl.infoshare.clinicweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClinicWebApplication.class, args);


//        String path = "ClinicWeb/src/main/resources/doctors.json";
//        PersonDetails personDetails = new PersonDetails("Damian", "Korzen", "444-444-333", "7564932323", LocalDate.of(1975, 12, 12));
//        Address address = new Address();
//        Doctor doctor = new Doctor(personDetails, address, Specialization.CHIRURGY, false, false);
//        FileService fs = new FileService(new ObjectMapper());
//        fs.writeToFile(doctor, path);
    }

}
