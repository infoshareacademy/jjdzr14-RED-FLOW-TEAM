package pl.infoshare.clinicweb.patientCard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;


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

    public void patientCardUpdate(PatientCardDTO patientCardDTO) {
        PatientCard patientCard = patientCardMapper.toEntity(patientCardDTO);
        patientCardRepository.save(patientCard);
    }

    public void patientCardDelete(Long id) {
        patientCardRepository.findById(id).ifPresent(patientCardRepository::delete);
    }

    public void addPatientCard(PatientCardDTO patientCardDTO) {
        PatientCard patientCard = patientCardMapper.toEntity(patientCardDTO);
    }
}


