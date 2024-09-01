package pl.infoshare.clinicweb.visit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class VisitService  {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    public VisitDto convertToDto (Visit visit) {

        return visitMapper.toVisitDto(visit);

    }

    public Visit convertToEntity (VisitDto visitDto) {

        return visitMapper.toEntity(visitDto);
    }

}