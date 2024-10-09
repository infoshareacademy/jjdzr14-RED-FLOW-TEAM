package pl.infoshare.clinicweb.patientCard;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;


    @Autowired
    public PatientCardService(PatientCardRepository patientCardRepository, PatientCardMapper patientCardMapper) {
        this.patientCardRepository = patientCardRepository;
        this.patientCardMapper = patientCardMapper;

    }

    public List<PatientCardDTO> findAll() {
        return patientCardRepository.findAll()
                .stream()
                .map(patientCardMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientCardDTO findById(Long id) {
        return patientCardRepository
                .findById(id)
                .map(patientCardMapper::toDto)
                .orElseThrow(()
                        -> new EntityNotFoundException(String.format("Patient card not found with given ID: %s", id)));
    }

    public void patientCardSave(PatientCardDTO patientCardDTO) {
        patientCardRepository.save(patientCardMapper.toEntity(patientCardDTO));

    }

    public void patientCardDelete(Long id) {
        patientCardRepository.findById(id).ifPresent(patientCardRepository::delete);
    }
}

