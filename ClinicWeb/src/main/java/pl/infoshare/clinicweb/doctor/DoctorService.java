package pl.infoshare.clinicweb.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public void addDoctor(Doctor user) {

        doctorRepository.save(user);
    }


    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }


    public void deleteDoctor(Long idDoctor) {

        doctorRepository.deleteById(idDoctor);
    }

    public void updateDoctor(DoctorDto doctorDto) {

        Doctor doctor = doctorMapper.toEntity(doctorDto);

        doctorRepository.save(doctor);
    }

    public void findDoctorByKey(String name, String surname) {

    }


    public DoctorDto convertToDto(Doctor doctor) {

        return doctorMapper.toDto(doctor);

    }

    public Doctor convertToEntity(DoctorDto dto) {

        Doctor doctor = doctorMapper.toEntity(dto);

        return doctorRepository.findById(doctor.getId()).get();
    }




}
