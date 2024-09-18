package pl.infoshare.clinicweb.patient;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.infoshare.clinicweb.patientCard.PatientCardRepository;
import pl.infoshare.clinicweb.patientCard.PatientCardService;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.visit.Visit;
import pl.infoshare.clinicweb.visit.VisitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final VisitRepository visitRepository;
    private final PatientCardRepository patientCardRepository;


    public void addPatient(Patient patient) {

        patientRepository.save(patient);
    }

    public Optional<PatientDto> findById(Long id) {

        return patientRepository.findById(id)
                .stream()
                .map(patientMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Patient not found with id %s", id)));
    }

    public List<Optional<PatientDto>> findAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }


    public void updatePatient(PatientDto patientDto, Address address) {

        Patient patient = patientMapper.toEntity(patientDto);
        patient.setAddress(address);

        patientRepository.save(patient);

    }

    @Transactional
    public void deletePatient(Long id) {
        List<Visit> visits = visitRepository.findAllByPatientId(id);
        if (!visits.isEmpty()) {
            visitRepository.deleteAll(visits);
        }
        patientCardRepository.findById(id).ifPresent(patientCardRepository::delete);
        patientRepository.findById(id).ifPresent(patientRepository::delete);
    }

    public void setPatientAttributes(Patient patient, PersonDetails personDetails,
                                     Address address) {

        patient.setPersonDetails(personDetails);
        patient.setAddress(address);


    }


}