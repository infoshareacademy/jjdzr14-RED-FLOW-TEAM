package pl.infoshare.clinicweb.visit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.Optional;


@Component
@AllArgsConstructor

public class VisitMapper {


    public Optional<VisitDto> toVisitDto(Visit visit) {


        Optional<VisitDto> visitD = Optional.of(new VisitDto());
        VisitDto visitDto = visitD.get();

        visit.setId(visit.getId());
        visitDto.setVisitDate(visit.getVisitDate());
        visitDto.setVisitCancelled(visit.isCancelVisit());
        visitDto.setPatientName(visit.getPatient().getPersonDetails().getName());
        visitDto.setPatientSurname(visit.getPatient().getPersonDetails().getSurname());
        visitDto.setPatientPhoneNumber(visit.getPatient().getPersonDetails().getPhoneNumber());
        visitDto.setPatientPesel(visit.getPatient().getPersonDetails().getPesel());
        visitDto.setDoctorName(visit.getDoctor().getDetails().getName());
        visitDto.setDoctorSurname(visit.getDoctor().getDetails().getSurname());
        visitDto.setDoctorSpecialization(visitDto.getDoctorSpecialization());

        return Optional.of(visitDto);

    }

    public Visit toEntity(VisitDto visitDto) {

        Visit visit = new Visit();
        visit.setId(visitDto.getId());
        visit.setVisitDate(visitDto.getVisitDate());
        visit.setCancelVisit(visitDto.isVisitCancelled());

        if (visit.getPatient() == null) {
            Patient patient = new Patient();
            PersonDetails personDetails = new PersonDetails();
            patient.setPersonDetails(personDetails);
            visit.setPatient(patient);
        }


        if (visit.getDoctor() == null) {
            Doctor doctor = new Doctor();
            PersonDetails doctorDetails = new PersonDetails();
            doctor.setDetails(doctorDetails);
            visit.setDoctor(doctor);
        }

        visit.getPatient().getPersonDetails().setName(visitDto.getPatientName());
        visit.getPatient().getPersonDetails().setSurname(visitDto.getPatientSurname());
        visit.getPatient().getPersonDetails().setPhoneNumber(visitDto.getPatientPhoneNumber());
        visit.getPatient().getPersonDetails().setPesel(visitDto.getPatientPesel());

        visit.getDoctor().getDetails().setName(visitDto.getDoctorName());
        visit.getDoctor().getDetails().setSurname(visitDto.getDoctorSurname());
        visit.getDoctor().setSpecialization(visitDto.getDoctorSpecialization());

        return visit;
    }
}
