package pl.infoshare.clinicweb.visit;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (doctorId == null || patientId == null) {
            throw new IllegalArgumentException("Doctor ID and Patient ID cannot be null");
        }


        DoctorDto doctorDto = doctorService.findById(doctorId);
        PatientDto patientDto = patientService.findById(patientId);

        DoctorDto doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));
        Doctor entityDoctor = doctorMapper.toEntity(doctor);


        PatientDto patient = patientService.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        Patient entityPatient = patientMapper.toEntity(patient);

        visit.setDoctor(entityDoctor);
        visit.setPatient(entityPatient);


        visitRepository.save(visit);
    }

    public void updateVisit(VisitDto visitDto) {

        Visit visit = visitMapper.toEntity(visitDto);

        visitRepository.save(visit);
    }


    public List<VisitDto> findAllVisits() {

        return visitRepository.findAll()
                .stream()
                .map(visitMapper::toVisitDto)
                .collect(Collectors.toList());
    }

    public Page<VisitDto> findPage(int pageNumber) {

        Visit visit = visitMapper.toEntity(visitDto);
        visit.setCancelVisit(true);
        visitRepository.save(visit);

        final int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, Sort.by("id"));
        Page<Visit> entities = visitRepository.findAll(pageable);

        Page<VisitDto> visits = entities.map(visit -> {
            VisitDto visitDto = visitMapper.toVisitDto(visit);

            return visitDto;
        });

        return visits;
    }

    public void cancelVisit(VisitDto visitDto)  {

            if (visitDto.isVisitPastDate() || !visitDto.isVisitCancelled()) {

                Visit visit = visitMapper.toEntity(visitDto);

                visit.setCancelVisit(true);

                visitRepository.save(visit);
            }
    }

    public void deleteVisit(Visit visit) {

        visitRepository.findById(visit.getId()).ifPresent(visitRepository::delete);
    }

    public void setVisitAttributes(Patient patient, Doctor doctor, Visit visit) {

        visit.setPatient(patient);
        visit.setDoctor(doctor);

    }

    public VisitDto findVisitById(Long id) {

        return visitRepository
                .findById(id)
                .map(visitMapper::toVisitDto)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Visit not found with given ID: %d", id)));
}