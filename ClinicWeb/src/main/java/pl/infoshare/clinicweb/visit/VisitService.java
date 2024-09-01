package pl.infoshare.clinicweb.visit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class VisitService {


    private VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {

        this.visitRepository = visitRepository;
    }

    public void saveVisit(Visit visit) {

        visitRepository.save(visit);
    }

    public void updateVisit(Visit visit) {

        visitRepository.save(visit);
    }

    public List<Visit> getAllVisits() {

        return visitRepository.findAll(Sort.by(Sort.Direction.DESC, "visitDate"));

    }

    public void cancelVisit(Visit visit) {

        visit.setCancelVisit(true);

        visitRepository.save(visit);
    }

    public void deleteVisit(Visit visit) {

        visitRepository.findById(visit.getId()).ifPresent(visitRepository::delete);
    }







}