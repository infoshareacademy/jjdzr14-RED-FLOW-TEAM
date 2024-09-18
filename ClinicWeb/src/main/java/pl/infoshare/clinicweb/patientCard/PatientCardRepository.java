package pl.infoshare.clinicweb.patientCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.infoshare.clinicweb.visit.Visit;

import java.util.List;

@Repository
public interface PatientCardRepository extends JpaRepository<PatientCard, Long> {

}
