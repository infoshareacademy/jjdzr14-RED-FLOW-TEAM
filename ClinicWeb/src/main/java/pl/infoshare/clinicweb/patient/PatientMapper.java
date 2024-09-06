package pl.infoshare.clinicweb.patient;

import org.springframework.stereotype.Component;


@Component
public class PatientMapper {


    public PatientDto toDto (Patient patient) {

        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patient.getPersonDetails().getName());
        patientDto.setSurname(patient.getPersonDetails().getSurname());
        patientDto.setPesel(patient.getPersonDetails().getPesel());
        patientDto.setPhoneNumber(patient.getPersonDetails().getPhoneNumber());

        return patientDto;

    }

    public Patient toEntity(PatientDto patientDto) {

        Patient patient = new Patient();
        patient.setId(patientDto.getId());

        return patient;

    }
}
