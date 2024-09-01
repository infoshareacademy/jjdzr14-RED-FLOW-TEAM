package pl.infoshare.clinicweb.doctor;


import org.springframework.stereotype.Service;


@Service
public class DoctorService  {

    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorMapper doctorMapper) {

        this.doctorMapper = doctorMapper;

    }

    public DoctorDto convertToDto(Doctor doctor) {

        return doctorMapper.toDto(doctor);

    }

    public Doctor convertToEntity(DoctorDto dto) {

        return doctorMapper.toEntity(dto);
    }

}
