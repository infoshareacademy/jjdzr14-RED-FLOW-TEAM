package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoctorMapper {

    public Optional<DoctorDto> toDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getDetails().getName());
        doctorDto.setSurname(doctor.getDetails().getSurname());
        doctorDto.setSpecialization(doctor.getSpecialization());

        return Optional.of(doctorDto);

    }

    public Doctor toEntity(DoctorDto doctorDto) {

        Doctor doctor = new Doctor();

        doctor.setId(doctorDto.getId());
        doctor.getDetails().setName(doctorDto.getName());
        doctor.getDetails().setSurname(doctorDto.getSurname());
        doctor.setSpecialization(doctorDto.getSpecialization());

        return doctor;

    }
}
