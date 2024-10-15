package pl.infoshare.clinicweb.patientCard;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.DoctorMapper;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientDto;
import pl.infoshare.clinicweb.patient.PatientMapper;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.visit.VisitService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientCardService {

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;
    private final VisitService visitService;
    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PatientMapper patientMapper;


    @Autowired
    public PatientCardService(PatientCardRepository patientCardRepository, PatientCardMapper patientCardMapper, VisitService visitService, DoctorMapper doctorMapper, DoctorService doctorService, PatientService patientService, PatientMapper patientMapper) {
        this.patientCardRepository = patientCardRepository;
        this.patientCardMapper = patientCardMapper;

        this.visitService = visitService;
        this.doctorMapper = doctorMapper;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    public List<PatientCardDTO> findAll() {
        return patientCardRepository.findAll()
                .stream()
                .map(patientCardMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientCardDTO findById(Long id) {
        return patientCardRepository
                .findByPatientId(id)
                .map(patientCardMapper::toDto)
                .orElseThrow(()
                        -> new EntityNotFoundException(String.format("Patient card not found with given ID: %s", id)));
    }

    public void patientCardSave(PatientCard patientCard, Long doctorId, Long patientId) {
        if (doctorId == null || patientId == null) {
            throw new IllegalArgumentException("Doctor ID and Patient ID cannot be null");
        }

        DoctorDto doctorDto = doctorService.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor ID not found: " + doctorId));
        Doctor entityDoctor = doctorMapper.toEntity(doctorDto);


        PatientDto patientEntity = patientService.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient ID not found: " + patientId));
Patient entityPatient = patientMapper.toEntity(patientEntity);

        patientCard.setPatient(entityPatient);
        patientCard.setDoctor(entityDoctor);


        patientCardRepository.save(patientCard);
    }


}

