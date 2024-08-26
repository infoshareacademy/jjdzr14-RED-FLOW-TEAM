package pl.infoshare.clinicweb.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    void addDoctor(Doctor user);

    void findAllDoctors();

    void deleteDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void findDoctorByKey(String name, String surname);
}
