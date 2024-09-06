package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDto toDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getDetails().getName());
        doctorDto.setSurname(doctor.getDetails().getSurname());
        doctorDto.setSpecialization(doctor.getSpecialization().getDescription());

        return doctorDto;

    }

    public Doctor toEntity(DoctorDto doctorDto) {

        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());

        return doctor;

    }
}
