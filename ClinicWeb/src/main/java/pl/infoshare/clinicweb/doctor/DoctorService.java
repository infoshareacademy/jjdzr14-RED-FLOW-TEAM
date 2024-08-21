package pl.infoshare.clinicweb.doctor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService implements DoctorRepository {

    private static final String DOCTOR_PATH = "ClinicWeb/src/main/resources/doctors.json";
    private final FileService fileService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DoctorService(FileService fileService) {
        this.fileService = fileService;
    }


    public void addDoctor(User user) {

    }

    @Override
    public void addDoctor(Doctor user) {

    }

    @Override
    public void findAllDoctors() {

    }

    @Override
    public void deleteDoctor(Doctor doctor) {

    }

    @Override
    public void updateDoctor(Doctor doctor) {

    }

    @Override
    public void findDoctorByKey(String name, String surname) {

    }

    public List<Doctor> getAll() {

        List<Doctor> doctorList = fileService.readFromFile(DOCTOR_PATH, new TypeReference<List<Doctor>>() {
        });
        return doctorList != null ? doctorList : new ArrayList<>();
    }

    public List<DoctorDto> findAll() {

        return getAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public List<Doctor> findBySpecialization(Specialization specialization) {

        return getAll()
                .stream()
                .filter(doctor -> doctor.getSpecialization().equals(specialization.getDescription()))
                .collect(Collectors.toList());

    }

    private DoctorDto convertToDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setName(doctor.getPersonDetails().getName());
        doctorDto.setSurname(doctor.getPersonDetails().getSurname());
        doctorDto.setSpecialization(doctor.getSpecialization());

        return doctorDto;
    }

    public Doctor findByPesel(String pesel) {
        return getAll()
                .stream()
                .filter(doctor -> doctor.getPersonDetails().getPesel().equals(pesel))
                .findAny().orElse(null);

    }

    public DoctorDto doctorDtoByPesel(String pesel) {
        return getAll()
                .stream()
                .filter(doctor -> doctor.getPersonDetails().getPesel().equals(pesel))
                .map(this::convertToDto)
                .findAny().orElse(null);

    }
  
    public void saveDoctor(Doctor doctor) {

        doctor.setDateOfBirth(doctor);

        fileService.writeToFile(doctor, DOCTOR_PATH);

    }
    public void setDoctorAttributes(Doctor doctor, PersonDetails personDetails, Address address, Specialization specialization) {

        doctor.setSpecialization(specialization.getDescription());
        doctor.setAddress(address);
        doctor.setPersonDetails(personDetails);

    }

    public void saveOrUpdateDoctor(Doctor doctor, Address address) {

        Doctor doctorByPesel = findByPesel(doctor.getPersonDetails().getPesel());
        if (doctorByPesel != null) {
            doctorByPesel.getPersonDetails().setName(doctor.getPersonDetails().getName());
            doctorByPesel.getPersonDetails().setSurname(doctor.getPersonDetails().getSurname());
            doctorByPesel.getPersonDetails().setGender(doctor.getPersonDetails().getGender());
            doctorByPesel.getAddress().setCountry(doctor.getAddress().getCountry());
            doctorByPesel.getAddress().setStreet(doctor.getAddress().getStreet());
            doctorByPesel.getAddress().setCity(doctor.getAddress().getCity());
            doctorByPesel.getAddress().setHouseNumber(doctor.getAddress().getHouseNumber());
            doctorByPesel.getAddress().setFlatNumber(doctor.getAddress().getFlatNumber());

            saveDoctor(doctorByPesel);

            removeFromFile(doctorByPesel.getPersonDetails().getPesel(), DOCTOR_PATH);

        } else {
            saveDoctor(new Doctor());
        }
    }

    public void removeFromFile(String pesel, String filePath) throws RuntimeException {

        List<Doctor> doctors = fileService.readFromFile(DOCTOR_PATH, new TypeReference<List<Doctor>>() {
        });

        mapper.registerModule(new JavaTimeModule());

        Iterator<Doctor> iterator = doctors.iterator();
        while (iterator.hasNext()) {
            Doctor doctor = iterator.next();
            if (doctor.getPersonDetails().getPesel().equals(pesel)) {
                iterator.remove();
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doctors);
            fileWriter.write(jsonPretty);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Object remove(Doctor pesel) {
        removeFromFile(pesel.getPersonDetails().getPesel(), DOCTOR_PATH);
        return null;
    }

}
