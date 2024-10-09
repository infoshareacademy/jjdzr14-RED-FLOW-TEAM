package pl.infoshare.clinicweb.patientCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.infoshare.clinicweb.visit.VisitDto;

import java.util.Optional;

@Repository
public interface PatientCardRepository extends JpaRepository<PatientCard, Long> {
    @Query("SELECT p FROM PatientCard p WHERE p.patient.id=:patientId")
    Optional<PatientCard> findByPatientId(Long patientId);

}
