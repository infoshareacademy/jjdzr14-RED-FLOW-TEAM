package pl.infoshare.clinicweb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args){
        SpringApplication.run(ClinicWebApplication.class, args);

    }
}
