package pl.infoshare.clinicweb.patient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.file.FileService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements PatientRepository {

    private static final String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";
    private final FileService fileService;
    ObjectMapper mapper = new ObjectMapper();

    public PatientService(FileService fileService) {

        this.fileService = fileService;
    }

    public void savePatient(Patient patient) {
        patient.setDateOfBirth(patient);
        fileService.writeToFile(patient, PATIENT_PATH);

    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = fileService.readFromFile(PATIENT_PATH, new TypeReference<List<Patient>>() {
        });
        return patients != null ? patients : new ArrayList<>();
    }


    public List<PatientDto> findAll() {
        return getAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());


    }

    public PatientDto convertToDto(Patient patient) {

        PatientDto patientDto = new PatientDto();

        patientDto.setName(patient.getPersonDetails().getName());
        patientDto.setSurname(patient.getPersonDetails().getSurname());
        patientDto.setPhoneNumber(patient.getPersonDetails().getPhoneNumber());
        patientDto.setPesel(patient.getPersonDetails().getPesel());


        return patientDto;
    }

    public Patient findByPesel(String pesel) {
        List<Patient> patients = getAll();
        Optional<Patient> patOpt = Optional.of(new Patient());
        if (patients == null) {
            return patOpt.orElseThrow();
        }
        return patients.stream()
                .filter(patient -> patient.getPersonDetails().getPesel().equals(pesel))
                .findFirst()
                .orElse(null);
    }

    public void saveOrUpdatePatient(Patient patient, Address address) {
        Patient patientByPesel = findByPesel(patient.getPersonDetails().getPesel());
        if (patientByPesel != null) {
            patientByPesel.getPersonDetails().setName(patient.getPersonDetails().getName());
            patientByPesel.getPersonDetails().setSurname(patient.getPersonDetails().getSurname());
            patientByPesel.getAddress().setCountry(patient.getAddress().getCountry());
            patientByPesel.getAddress().setStreet(patient.getAddress().getStreet());
            patientByPesel.getAddress().setCity(patient.getAddress().getCity());
            patientByPesel.getAddress().setHouseNumber(patient.getAddress().getHouseNumber());
            patientByPesel.getAddress().setFlatNumber(patient.getAddress().getFlatNumber());
            savePatient(patientByPesel);
            removeFromFile(patientByPesel.getPersonDetails().getPesel(), PATIENT_PATH);
        } else {
            savePatient(new Patient());
        }
    }

    public void removeFromFile(String pesel, String filePath) throws RuntimeException {
        List<Patient> patients = fileService.readFromFile(PATIENT_PATH, new TypeReference<List<Patient>>() {
        });
        mapper.registerModule(new JavaTimeModule());
        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            if (patient.getPersonDetails().getPesel().equals(pesel)) {
                iterator.remove();
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patients);
            fileWriter.write(jsonPretty);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Object remove(Patient pesel) {
        removeFromFile(pesel.getPersonDetails().getPesel(), PATIENT_PATH);
        return null;
    }
    public void deleteFilePaitent(){
        fileService.deleteData(PATIENT_PATH);
    }
}