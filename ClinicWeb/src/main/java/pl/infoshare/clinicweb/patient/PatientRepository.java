package pl.infoshare.clinicweb.patient;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository {

    void savePatient(Patient patient);

    List<Patient> getAll();


}
