package pl.infoshare.clinicweb.patientCard;

import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.visit.Visit;


@Component
public class PatientCardMapper {

    public PatientCardDTO toDto(PatientCard patientCard) {
        PatientCardDTO patientCardDTO = new PatientCardDTO();

        patientCardDTO.setPatientPesel(patientCard.getPatient().getPersonDetails().getPesel());
        patientCardDTO.setPatientFirstName(patientCard.getPatient().getPersonDetails().getName());
        patientCardDTO.setPatientLastName(patientCard.getPatient().getPersonDetails().getSurname());
        patientCardDTO.setDoctorFirstName(patientCard.getDoctor().getDetails().getName());
        patientCardDTO.setDoctorLastName(patientCard.getDoctor().getDetails().getSurname());
        patientCardDTO.setSymptoms(patientCard.getSymptoms());

        patientCardDTO.setDateOfVisit(patientCard.getDateOfVisit());
        patientCardDTO.setNoteDoctor(patientCard.getNoteDoctor());

        patientCardDTO.setDiagnosis(patientCard.getDiagnosis());
        patientCardDTO.setTreatment(patientCard.getTreatment());
        return (patientCardDTO);
    }

    public PatientCard toEntity(PatientCardDTO patientCardDTO) {

        PatientCard patientCard = new PatientCard();
        patientCard.setId(patientCardDTO.getId());
        patientCard.setDateOfVisit(patientCardDTO.getDateOfVisit());


        if (patientCardDTO.getPatientFirstName() != null && patientCardDTO.getPatientLastName() != null) {
            Patient patient = new Patient();
            PersonDetails patientDetails = new PersonDetails();
            patientDetails.setName(patientCardDTO.getPatientFirstName());
            patientDetails.setSurname(patientCardDTO.getPatientLastName());
            patient.setPersonDetails(patientDetails);
            if (patientCardDTO.getId() != null) {
                patient.setId(patientCardDTO.getId());
                patientCard.setPatient(patient);
            }


            if (patientCardDTO.getDoctorFirstName() != null && patientCardDTO.getDoctorLastName() != null) {
                Doctor doctor = new Doctor();
                PersonDetails doctorDetails = new PersonDetails();
                doctorDetails.setName(patientCardDTO.getDoctorFirstName());
                doctorDetails.setSurname(patientCardDTO.getDoctorLastName());
                doctor.setDetails(doctorDetails);
                if (patientCardDTO.getId() != null) {
                    doctor.setId(patientCardDTO.getId());
                }
                patientCard.setDoctor(doctor);
            }


            patientCard.setSymptoms(patientCardDTO.getSymptoms());
            patientCard.setNoteDoctor(patientCardDTO.getNoteDoctor());
            patientCard.setDiagnosis(patientCardDTO.getDiagnosis());
            patientCard.setTreatment(patientCardDTO.getTreatment());

            return patientCard;
        }

        return patientCard;
    }
}
