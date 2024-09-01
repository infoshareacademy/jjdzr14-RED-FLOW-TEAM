package pl.infoshare.clinicweb.patient;

import org.springframework.stereotype.Service;



@Service
public class PatientService {

    private final PatientMapper patientMapper;

    public PatientService(PatientMapper patientMapper) {

        this.patientMapper = patientMapper;

    }

    public PatientDto convertToDto(Patient patient) {

        return patientMapper.toDto(patient);

    }

    public Patient convertToEntity(PatientDto dto) {

        return patientMapper.toEntity(dto);
    }


}