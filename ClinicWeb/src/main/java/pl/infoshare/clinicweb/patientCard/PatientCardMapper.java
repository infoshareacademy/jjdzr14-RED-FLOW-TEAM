package pl.infoshare.clinicweb.patientCard;

import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PatientCardMapper {

    public PatientCardDTO  toDto(PatientCard patientCard) {
        PatientCardDTO patientCardDTO = new PatientCardDTO();

        patientCardDTO.setPatientPesel(patientCard.getPatient().getPersonDetails().getPesel());
        patientCardDTO.setPatientFirstName(patientCard.getPatient().getPersonDetails().getName());
        patientCardDTO.setPatientLastName(patientCard.getPatient().getPersonDetails().getSurname());
        patientCardDTO.setDoctorFirstName(patientCard.getDoctor().getDetails().getName());
        patientCardDTO.setDoctorLastName(patientCard.getDoctor().getDetails().getSurname());
        patientCardDTO.setSymptoms(patientCard.getSymptoms());

        patientCardDTO.setDateOfVisit(patientCard.getDateOfVisit());
        patientCardDTO.setNoteDoctor(patientCard.getNoteDoctor());
        patientCardDTO.setNoteMedicalHistory(patientCard.getNoteMedicalHistory());
        patientCardDTO.setDiagnosis(patientCard.getDiagnosis());
        patientCardDTO.setTreatment(patientCard.getTreatment());
        return (patientCardDTO);
    }
    public PatientCard toEntity(PatientCardDTO patientCardDTO) {
        PatientCard patientCard = new PatientCard();

        Patient patient = new Patient();
        PersonDetails patientDetails = new PersonDetails();
        patientDetails.setName(patientCardDTO.getPatientFirstName());
        patientDetails.setSurname(patientCardDTO.getPatientLastName());
        patient.setPersonDetails(patientDetails);
        patientCard.setPatient(patient);

        Doctor doctor = new Doctor();
        PersonDetails doctorDetails = new PersonDetails();
        doctorDetails.setName(patientCardDTO.getDoctorFirstName());
        doctorDetails.setSurname(patientCardDTO.getDoctorLastName());
        doctor.setDetails(doctorDetails);
        patientCard.setDoctor(doctor);

        patientCard.setSymptoms(patientCardDTO.getSymptoms());
        patientCard.setDateOfVisit(patientCardDTO.getDateOfVisit());
        patientCard.setNoteDoctor(patientCardDTO.getNoteDoctor());
        patientCard.setNoteMedicalHistory(patientCardDTO.getNoteMedicalHistory());
        patientCard.setDiagnosis(patientCardDTO.getDiagnosis());
        patientCard.setTreatment(patientCardDTO.getTreatment());

        return patientCard;
    }

}
