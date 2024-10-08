package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoctorMapper {

    public Optional<DoctorDto> toDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setId(doctor.getId());
        doctorDto.setPesel(doctor.getDetails().getPesel());
        doctorDto.setName(doctor.getDetails().getName());
        doctorDto.setSurname(doctor.getDetails().getSurname());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setCountry(doctor.getAddress().getCountry());
        doctorDto.setCity(doctor.getAddress().getCity());
        doctorDto.setZipCode(doctor.getAddress().getZipCode());
        doctorDto.setFlatNumber(doctor.getAddress().getFlatNumber());
        doctorDto.setHouseNumber(doctor.getAddress().getHouseNumber());
        doctorDto.setStreet(doctor.getAddress().getStreet());

        return Optional.of(doctorDto);

    }

    public Doctor toEntity(DoctorDto doctorDto) {

        Doctor doctor = new Doctor();

        doctor.setId(doctorDto.getId());
        doctor.getDetails().setPesel(doctorDto.getPesel());
        doctor.getDetails().setName(doctorDto.getName());
        doctor.getDetails().setSurname(doctorDto.getSurname());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.getAddress().setCountry(doctorDto.getCountry());
        doctor.getAddress().setCity(doctorDto.getCity());
        doctor.getAddress().setZipCode(doctorDto.getZipCode());
        doctor.getAddress().setFlatNumber(doctorDto.getFlatNumber());
        doctor.getAddress().setHouseNumber(doctorDto.getHouseNumber());
        doctor.getAddress().setStreet(doctorDto.getStreet());

        return doctor;

    }
}
