package pl.infoshare.clinicweb.patient;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientService  {

    PatientRepository patientRepository;

    public  PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    public void addPatient(Patient patient) {

        patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {

       return patientRepository.getReferenceById(id);

    }

    public List<Patient> findAllPatients() {

        return patientRepository.findAll();
    }


    public void updatePatient(Patient patient) {

        patientRepository.save(patient);

    }

    public void deletePatient(Patient patient) {

        patientRepository.findById(patient.getId()).ifPresent(patientRepository::delete);
    }




}