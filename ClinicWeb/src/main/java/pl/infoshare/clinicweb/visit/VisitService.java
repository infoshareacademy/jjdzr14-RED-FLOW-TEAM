package pl.infoshare.clinicweb.visit;

import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.file.FileService;

@Service
public class VisitService implements VisitRepository {

    private final String VISITS_PATH = "ClinicWeb/src/main/resources/visits.json";
    private final FileService fileService;

    public VisitService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void saveVisit(Visit visit) {

        fileService.writeToFile(visit, VISITS_PATH);

    }
}
