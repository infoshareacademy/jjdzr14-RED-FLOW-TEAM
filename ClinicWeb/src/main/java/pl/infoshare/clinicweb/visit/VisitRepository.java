package pl.infoshare.clinicweb.visit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.infoshare.clinicweb.doctor.Doctor;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v FROM Visit v WHERE v.patient.id = :patientId")
    List<Visit> findAllByPatientId(Long patientId);

    @Query("SELECT v FROM Visit v WHERE v.doctor = :doctor AND v.visitTime BETWEEN :startTime AND :endTime")
    List<Visit> findByDoctorAndVisitTimeBetween(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime);
}
