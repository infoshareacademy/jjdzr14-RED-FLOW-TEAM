package pl.infoshare.clinicweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;

import java.util.List;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

String path = "src/main/resources/patients.json";
        SpringApplication.run(ClinicWebApplication.class, args);
        ObjectMapper mapper = new ObjectMapper();
        FileService fileService = new FileService(mapper);
        List<Patient> patients = fileService.readFromFile(path, new TypeReference<List<Patient>>() {
        });

    }
}