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

        SpringApplication.run(ClinicWebApplication.class, args);
        String path= "patients.json";
        ObjectMapper objectMapper = new ObjectMapper();
        FileService fileService = new FileService(objectMapper);
        List<Patient>newList= fileService.readFromFile(path,new TypeReference<List<Patient>>(){});
PatientService patientService = new PatientService(fileService,newList);
        System.out.println(patientService.findByPesel("75010112345"));
    }
}
