package pl.infoshare.clinicweb.patient;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public void addPatient(Patient patient) {

        patientRepository.save(patient);
    }

    public PatientDto findById(Long id) {

        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Patient not found with id %s", id)));
    }

    public PatientDto findByPesel(String pesel) {

        return patientRepository.findByPesel(pesel)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Patient not found with pesel %s", pesel)));
    }

    public List<PatientDto> findAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<PatientDto> findPage(int pageNumber) {

        final int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id"));
        Page<Patient> entities = patientRepository.findAll(pageable);

        Page<PatientDto> patients = entities.map(patient -> {
            PatientDto patientDto = patientMapper.toDto(patient);

            return patientDto;
        });

        return patients;
    }


    public void updatePatient(PatientDto patientDto, Address address) {

        Patient patient = patientMapper.toEntity(patientDto);
        patient.setAddress(address);

        patientRepository.save(patient);

    }

    public void deletePatient(Long id) {

        patientRepository.findById(id).ifPresent(patientRepository::delete);
    }

    public void setPatientAttributes(Patient patient, PersonDetails personDetails,
                                     Address address) {

        patient.setPersonDetails(personDetails);
        patient.setAddress(address);


    }


}