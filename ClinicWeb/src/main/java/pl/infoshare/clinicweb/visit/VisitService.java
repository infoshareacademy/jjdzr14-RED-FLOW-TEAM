package pl.infoshare.clinicweb.visit;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.DoctorMapper;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientDto;
import pl.infoshare.clinicweb.patient.PatientMapper;
import pl.infoshare.clinicweb.patient.PatientService;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class VisitService {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public void saveVisit(Visit visit, Long doctorId, Long patientId) {

        DoctorDto doctorDto = doctorService.findById(doctorId).get();
        PatientDto patientDto = patientService.findById(patientId).get();

        Doctor doctor = doctorMapper.toEntity(doctorDto);
        Patient patient = patientMapper.toEntity(patientDto);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        visitRepository.save(visit);
    }

    public void updateVisit(VisitDto visitDto) {

        Visit visit = visitMapper.toEntity(visitDto);

        visitRepository.save(visit);
    }

    public List<Visit> getAllVisits() {

        return visitRepository.findAll(Sort.by(Sort.Direction.DESC, "visitDate"));

    }

    public void cancelVisit(VisitDto visitDto) {

        Visit visit = visitMapper.toEntity(visitDto);

        visit.setCancelVisit(true);

        visitRepository.save(visit);
    }

    public void deleteVisit(Visit visit) {

        visitRepository.findById(visit.getId()).ifPresent(visitRepository::delete);
    }

    public void setVisitAttributes(Patient patient, Doctor doctor, Visit visit) {

        visit.setPatient(patient);
        visit.setDoctor(doctor);

    }

    public Optional<VisitDto> findVisitById(Long id) {

        return visitRepository.findById(id)
                .stream()
                .map(visitMapper::toVisitDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("No visit found with id %s", id)));
    }
}