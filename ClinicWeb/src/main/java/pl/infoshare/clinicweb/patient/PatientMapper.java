package pl.infoshare.clinicweb.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.doctor.DoctorService;

@Component
@AllArgsConstructor
public class PatientMapper {

    private final PatientRepository patientRepository;
    private final DoctorService doctorService;



    public PatientDto toDto (Patient patient) {

        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patient.getPersonDetails().getName());
        patientDto.setSurname(patient.getPersonDetails().getSurname());
        patientDto.setPesel(patient.getPersonDetails().getPesel());
        patientDto.setPhoneNumber(patient.getPersonDetails().getPhoneNumber());
        patientDto.setDoctor(doctorService.convertToDto(patient.getDoctor()));


        return patientDto;

    }

    public Patient toEntity(PatientDto patientDto) {

        return patientRepository.getReferenceById(patientDto.getId());;

    }
}
