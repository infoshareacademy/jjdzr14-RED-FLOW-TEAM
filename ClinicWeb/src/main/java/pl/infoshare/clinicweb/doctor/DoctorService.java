package pl.infoshare.clinicweb.doctor;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService implements DoctorRepository {

    private static final String DOCTOR_PATH = "ClinicWeb/src/main/resources/doctors.json";
    private final FileService fileService;

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
        return doctorList;
    }

    public List<DoctorDto> findAll() {

        return getAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public List<DoctorDto> findBySpecialization(Specialization specialization) {

        return getAll()
                .stream()
                .filter(doctor -> doctor.getSpecialization().equals(specialization.getDescription()))
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    private DoctorDto convertToDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();

        doctorDto.setName(doctor.getPersonDetails().getName());
        doctorDto.setSurname(doctor.getPersonDetails().getSurname());
        doctorDto.setSpecialization(doctor.getSpecialization());

        return doctorDto;
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
}
