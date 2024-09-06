package pl.infoshare.clinicweb.visit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class VisitMapper {


    public VisitDto toVisitDto(Visit visit) {


        VisitDto visitDto = new VisitDto();
        visit.setId(visit.getId());
        visitDto.setVisitDate(visit.getVisitDate());
        visitDto.setVisitCancelled(visit.isCancelVisit());

        return visitDto;

    }

    public Visit toEntity (VisitDto visitDto) {

        Visit visit = new Visit();
        visit.setId(visitDto.getId());

        return visit;

    }
}
