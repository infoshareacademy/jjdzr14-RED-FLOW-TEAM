package pl.infoshare.clinicweb.patientCard;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientCardMapper {

    public Optional<PatientCardDTO> toDto(PatientCard patientCard) {
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
        return Optional.of(patientCardDTO);
    }

    public PatientCard toEntity(PatientCardDTO patientCardDTO) {
        PatientCard patientCard = new PatientCard();
        patientCard.getPersonDetails().setName(patientCardDTO.getPatientFirstName());
        patientCard.getPersonDetails().setSurname(patientCardDTO.getPatientLastName());
        patientCard.getPersonDetails().setName(patientCardDTO.getDoctorFirstName());
        patientCard.getPersonDetails().setSurname(patientCardDTO.getDoctorLastName());
        patientCard.setSymptoms(patientCardDTO.getSymptoms());
        patientCard.setDateOfVisit(patientCardDTO.getDateOfVisit());
        patientCard.setNoteDoctor(patientCardDTO.getNoteDoctor());
        patientCard.setNoteMedicalHistory(patientCardDTO.getNoteMedicalHistory());
        patientCard.setDiagnosis(patientCardDTO.getDiagnosis());
        patientCard.setTreatment(patientCardDTO.getTreatment());
        return patientCard;
    }

}
