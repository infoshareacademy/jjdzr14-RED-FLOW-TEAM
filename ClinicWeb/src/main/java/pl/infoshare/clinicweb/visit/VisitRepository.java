package pl.infoshare.clinicweb.visit;

import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository {

    void saveVisit(Visit visit);
}
