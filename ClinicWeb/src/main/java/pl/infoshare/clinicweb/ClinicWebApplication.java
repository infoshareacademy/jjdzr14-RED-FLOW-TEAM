package pl.infoshare.clinicweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.User;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClinicWebApplication.class, args);

        String path = "/home/amanda/IdeaProjects/jjdzr14-RED-FLOW-TEAM-correct/ClinicWeb/src/main/resources/doctors.json";
        User user = new User();
        PersonDetails personDetails = new PersonDetails();
        Address address = new Address();
        Doctor doctor = new Doctor(user, personDetails, address, "Neurolog", false, false);
        FileService fs = new FileService(new ObjectMapper());
        fs.writeToFile(doctor, path);
    }

}
