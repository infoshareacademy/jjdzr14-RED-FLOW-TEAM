package pl.infoshare.clinicweb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.file.GeneratorData;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;

import java.util.List;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicWebApplication.class, args);
        GeneratorData generatorData = new GeneratorData();
       generatorData.writeRandomPatients(10);
    }
}