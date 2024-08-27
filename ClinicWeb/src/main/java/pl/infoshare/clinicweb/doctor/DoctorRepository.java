package pl.infoshare.clinicweb.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {


    void findDoctorByKey(String name, String surname);
}
