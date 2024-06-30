package pl.infoshare.clinicweb.patient;

import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.file.FileService;

@Service
public class PatientService implements PatientRepository {

    private static final String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";

    private final FileService fileService;


    public PatientService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void savePatient(Patient patient) {

        fileService.writeToFile(patient, PATIENT_PATH);

    }
}
