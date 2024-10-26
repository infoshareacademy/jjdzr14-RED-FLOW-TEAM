package pl.infoshare.clinicweb.patient;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patientCard.PatientCardRepository;
import pl.infoshare.clinicweb.patientCard.PatientCardService;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.visit.Visit;
import pl.infoshare.clinicweb.visit.VisitRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final VisitRepository visitRepository;
    private final PatientCardRepository patientCardRepository;


    public void addPatient(Patient patient) {

        patientRepository.save(patient);
    }

    public PatientDto findById(Long id) {

        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Patient not found with id %s", id)));
    }

    public PatientDto findByPesel(String pesel) {

        return patientRepository.findByPesel(pesel.trim())
                .map(patientMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Patient not found with pesel %s", pesel)));
    }

    public List<PatientDto> findAllPatients() {
      return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
               .collect(Collectors.toList());
    }

    public Page<PatientDto> findPage(int pageNumber) {

        final int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id"));
        Page<Patient> entities = patientRepository.findAll(pageable);

        Page<PatientDto> patients = entities.map(patient -> {
            PatientDto patientDto = patientMapper.toDto(patient);

            return patientDto;
        });

        return patients;
    }


    public void updatePatient(PatientDto patientDto, Address address) {

        Patient patient = patientMapper.toEntity(patientDto);
        patient.setAddress(address);

        patientRepository.save(patient);

    }

    @Transactional
    public void deletePatient(Long id) {
        List<Visit> visits = visitRepository.findAllByPatientId(id);
        if (!visits.isEmpty()) {
            visitRepository.deleteAll(visits);
        }
        patientCardRepository.findById(id).ifPresent(patientCardRepository::delete);
        patientRepository.findById(id).ifPresent(patientRepository::delete);
    }

    public void setPatientAttributes(Patient patient, PersonDetails personDetails,
                                     Address address) {

        LocalDate localDateBirthDate = PatientService.decodeDateOfBirth(personDetails.getPesel());

        patient.setPersonDetails(personDetails);
        patient.getPersonDetails().setBirthDate(localDateBirthDate);
        patient.setAddress(address);

    }

    public static LocalDate decodeDateOfBirth(String pesel) {


        String secondYearPart = pesel.substring(0, 2);
        String month = pesel.substring(2, 4);
        int day = Integer.parseInt(pesel.substring(4, 6));

        String decodedYearPart;
        if (month.startsWith("8") || month.startsWith("9")) {
            decodedYearPart = "18";
        } else if (month.startsWith("0") || month.startsWith("1")) {
            decodedYearPart = "19";
        } else if (month.startsWith("2") || month.startsWith("3")) {
            decodedYearPart = "20";
        } else if (month.startsWith("4") || month.startsWith("5")) {
            decodedYearPart = "21";
        } else {
            decodedYearPart = "22";
        }


        int monthValue = Integer.parseInt(month) - (decodedYearPart.equals("18") ? 80 : 0);
        int fullYear = Integer.parseInt(decodedYearPart + secondYearPart);

        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.of(fullYear, monthValue, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid date of birth from PESEL");
        }

        return dateOfBirth;
    }



}