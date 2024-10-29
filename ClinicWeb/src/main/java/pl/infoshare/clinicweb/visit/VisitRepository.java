package pl.infoshare.clinicweb.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.infoshare.clinicweb.doctor.Doctor;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAllByPatientId(Long patientId);

    List<Visit> findByDoctorAndVisitTimeBetween(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime);
}
