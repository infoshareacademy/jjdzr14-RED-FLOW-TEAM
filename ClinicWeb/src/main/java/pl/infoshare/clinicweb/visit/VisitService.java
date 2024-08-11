package pl.infoshare.clinicweb.visit;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Patient;

import java.util.List;

@Service
public class VisitService implements VisitRepository {

    private static final String VISITS_PATH = "ClinicWeb/src/main/resources/visits.json";
    private final FileService fileService;

    public VisitService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void saveVisit(Visit visit) {

        fileService.writeToFile(visit, VISITS_PATH);

    }


    public void setVisitAttributes(Patient patient, DoctorDto doctor, Visit visit) {
        if (patient != null) {
            visit.setPatient(patient);

        }

        if (doctor != null) {
            visit.setDoctor(doctor);
        }
    }

    public List<Visits> getAll() {
        List<Visits> visits = fileService.readFromFile(VISITS_PATH, new TypeReference<List<Visits>>() {
        });
        return visits.stream().sorted((o1, o2) -> o2.getVisitDate().compareTo(o1.getVisitDate())).toList();
    }

}
