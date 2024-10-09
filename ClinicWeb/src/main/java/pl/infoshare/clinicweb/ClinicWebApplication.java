package pl.infoshare.clinicweb;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patientCard.PatientCard;
import pl.infoshare.clinicweb.patientCard.PatientCardService;
import pl.infoshare.clinicweb.visit.VisitDto;

import java.util.Optional;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClinicWebApplication.class, args);

    }
}