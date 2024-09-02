package pl.infoshare.clinicweb.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository repository;
    private final DoctorMapper doctorMapper;

    public void addDoctor(Doctor user) {

        repository.save(user);
    }


    public List<Doctor> findAllDoctors() {
        return repository.findAll();
    }


    public void deleteDoctor(Long idDoctor) {

        repository.deleteById(idDoctor);
    }

    public void updateDoctor(Doctor doctor) {

        repository.save(doctor);
    }

    public void findDoctorByKey(String name, String surname) {

    }

    public void deleteById(Long id) {
    }


    public DoctorDto convertToDto(Doctor doctor) {

        return doctorMapper.toDto(doctor);

    }

    public Doctor convertToEntity(DoctorDto dto) {

        return doctorMapper.toEntity(dto);
    }

    public Doctor findDoctorById(Long id) {

        return repository.getReferenceById(id);

    }



}
