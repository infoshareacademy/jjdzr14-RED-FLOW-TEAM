package pl.infoshare.clinicweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.visit.Visit;
import pl.infoshare.clinicweb.visit.VisitService;

import java.util.List;
import java.util.UUID;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

String path = "src/main/resources/patients.json";
String patsVisits = "src/main/resources/visits.json";
        SpringApplication.run(ClinicWebApplication.class, args);
        ObjectMapper mapper = new ObjectMapper();
        FileService fileService = new FileService(mapper);
        List<Patient> patients = fileService.readFromFile(path, new TypeReference<List<Patient>>() {
        });
        List<Visit> visits = fileService.readFromFile(patsVisits, new TypeReference<List<Visit>>() {
        });
        VisitService visitService = new VisitService(fileService);
        Visit visitFoeUUID = visitService.getVisitFoeUUID(UUID.fromString("f712a624-c1fd-4813-8fb9-d8d921c46e3f"));
        System.out.println(visitFoeUUID);
    }
}