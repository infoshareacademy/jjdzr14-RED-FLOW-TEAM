package pl.infoshare.clinicweb.patient;

import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PatientMapper {


    public Optional<PatientDto> toDto (Patient patient) {

        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patient.getPersonDetails().getName());
        patientDto.setSurname(patient.getPersonDetails().getSurname());
        patientDto.setPesel(patient.getPersonDetails().getPesel());
        patientDto.setPhoneNumber(patient.getPersonDetails().getPhoneNumber());

        return Optional.of(patientDto);

    }

    public Patient toEntity(PatientDto patientDto) {

        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.getPersonDetails().setName(patientDto.getName());
        patient.getPersonDetails().setSurname(patientDto.getSurname());
        patient.getPersonDetails().setPesel(patientDto.getPesel());
        patient.getPersonDetails().setPhoneNumber(patientDto.getPhoneNumber());

        return patient;

    }
}
