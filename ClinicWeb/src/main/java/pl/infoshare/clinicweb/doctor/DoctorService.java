package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.repository = doctorRepository;
    }


    public void addDoctor(Doctor user) {
        addDoctor(user);
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

    public void deleteById(Integer integer) {
    }

}
