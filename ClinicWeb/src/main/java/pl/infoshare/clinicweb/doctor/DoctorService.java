package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    public void addDoctor(Doctor user) {
        doctorRepository.save(user);
    }


    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }


    public void deleteDoctor(Long idDoctor) {
        doctorRepository.findById(idDoctor).ifPresent(doctorRepository::delete);
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void findDoctorByKey(String name, String surname) {
    }

    public Doctor findDoctorById(Long id) {
        return doctorRepository.getReferenceById(id);
    }

    public void deleteById(Integer integer) {
    }

}
