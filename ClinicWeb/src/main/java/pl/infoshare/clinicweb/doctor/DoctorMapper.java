package pl.infoshare.clinicweb.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DoctorMapper {

    private final DoctorRepository doctorRepository;


    public DoctorDto toDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getPersonDetails().getName());
        doctorDto.setSurname(doctor.getPersonDetails().getSurname());
        doctorDto.setSpecialization(doctor.getSpecialization());

        return doctorDto;

    }

    public Doctor toEntity(DoctorDto doctorDto) {

        return doctorRepository.getReferenceById(doctorDto.getId());;

    }
}
