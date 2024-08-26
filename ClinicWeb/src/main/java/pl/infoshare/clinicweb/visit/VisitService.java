package pl.infoshare.clinicweb.visit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.patient.Patient;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class VisitService implements VisitRepository {

    private static final String VISITS_PATH = "ClinicWeb/src/main/resources/visits.json";
    private final FileService fileService;
    ObjectMapper mapper = new ObjectMapper();

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

    public List<Visit> getAll() {
        List<Visit> visits = fileService.readFromFile(VISITS_PATH, new TypeReference<List<Visit>>() {
        });
        if (visits == null||visits.isEmpty()) {
            return new ArrayList<>();
        }
        return visits.stream().sorted(Comparator.comparing(Visit::getVisitDate)).toList();
    }

    public void cancelVisit(Visit visit) {
        if (visit != null) {
            visit.setCancelVisit(true);
        }
    }

    public Visit getVisitFoeUUID(UUID uuid) {
        List<Visit> visitList = getAll();
        if (visitList == null) {
            throw new RuntimeException("Visit list is null");
        }

        return visitList.stream()
                .filter(visit -> visit.getNumberOfVisits().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Visit not found"));
    }

    public Object remove(UUID uuid, Visit visit) {
        removeFromFile(visit.getNumberOfVisits(), VISITS_PATH);
        return null;
    }

    public void removeFromFile(UUID uuid, String filePath) throws RuntimeException {
        List<Visit> visits = fileService.readFromFile(VISITS_PATH, new TypeReference<List<Visit>>() {
        });
        mapper.registerModule(new JavaTimeModule());
        Iterator<Visit> iterator = visits.iterator();
        while (iterator.hasNext()) {
            Visit visit = iterator.next();
            if (visit.getNumberOfVisits().equals(uuid)) {
                iterator.remove();
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(visits);
            fileWriter.write(jsonPretty);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}