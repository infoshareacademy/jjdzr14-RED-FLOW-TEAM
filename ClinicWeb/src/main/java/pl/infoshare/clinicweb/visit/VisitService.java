package pl.infoshare.clinicweb.visit;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

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

    public VisitDto convertToDto (Visit visit) {

        return visitMapper.toVisitDto(visit);

    }

    public Visit convertToEntity (VisitDto visitDto) {

        return visitMapper.toEntity(visitDto);
    }







}