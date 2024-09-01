package pl.infoshare.clinicweb.visit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.PatientService;

@Component
@AllArgsConstructor

public class VisitMapper {

    private final VisitRepository visitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public VisitDto toVisitDto(Visit visit) {


        VisitDto visitDto = new VisitDto();
        visit.setId(visit.getId());
        visitDto.setVisitDate(visit.getVisitDate());
        visitDto.setPatient(patientService.convertToDto(visit.getPatient()));
        visitDto.setDoctor(doctorService.convertToDto(visit.getDoctor()));
        visitDto.setVisitCancelled(visit.isCancelVisit());

        return visitDto;

    }

    public Visit toEntity (VisitDto visitDto) {

        return visitRepository.getReferenceById(visitDto.getId());;

    }
}
