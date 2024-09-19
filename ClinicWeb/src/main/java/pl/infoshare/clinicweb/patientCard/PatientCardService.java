package pl.infoshare.clinicweb.patientCard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientRepository;
import pl.infoshare.clinicweb.visit.VisitDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientCardService(PatientCardRepository patientCardRepository, PatientRepository patientRepository, PatientCardMapper patientCardMapper) {
        this.patientCardRepository = patientCardRepository;
        this.patientCardMapper = patientCardMapper;
        this.patientRepository = patientRepository;
    }

    public List<Optional<PatientCardDTO>> findAll() {
        return patientCardRepository.findAll()
                .stream()
                .map(patientCardMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PatientCardDTO> findById(Long id) {
        return patientCardRepository
                .findById(id)
                .stream()
                .map(patientCardMapper::toDto)
                .findFirst()
                .orElseThrow(()
                        -> new EntityNotFoundException(String.format("Patient card not found %s", id)));
    }

    public void patientCardSave(PatientCardDTO patientCardDTO) {
        PatientCard patientCard = patientCardMapper.toEntity(patientCardDTO);
        patientCardRepository.save(patientCard);
    }

    public void patientCardDelete(Long id) {
        patientCardRepository.findById(id).ifPresent(patientCardRepository::delete);
    }

    public PatientCard createOrGetPatientCard(Long patientId) {
        return patientCardRepository.findByPatientId(patientId)
                .orElseGet(() -> {

                    Patient patient = patientRepository.findById(patientId)
                            .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono pacjenta o ID: " + patientId));
                    PatientCard newCard = new PatientCard();

                    newCard.setPatient(patient);
                    newCard.setDateOfVisit(LocalDateTime.now());
                    return patientCardRepository.save(newCard);
                });
    }
}


