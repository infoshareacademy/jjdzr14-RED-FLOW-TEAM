package pl.infoshare.clinicweb.patient;


import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository {

    void savePatient(Patient patient);

    List<Patient> getAll();

    Patient findByPesel(String pesel);


}
