package pl.infoshare.clinicweb.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PatientService  {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public void addPatient(Patient patient) {

        patientRepository.save(patient);
    }

    public Patient findById(Long id) {

       return patientRepository.getReferenceById(id);

    }

    public List<Patient> findAllPatients() {

        return patientRepository.findAll();
    }


    public void updatePatient(Patient patient) {

        patientRepository.save(patient);

    }

    public void deletePatient(Patient patient) {

        patientRepository.findById(patient.getId()).ifPresent(patientRepository::delete);
    }

    public PatientDto convertToDto(Patient patient) {

        return patientMapper.toDto(patient);

    }

    public Patient convertToEntity(PatientDto dto) {

        Patient patient = patientMapper.toEntity(dto);

        return patientRepository.findById(patient.getId()).get();

    }




}