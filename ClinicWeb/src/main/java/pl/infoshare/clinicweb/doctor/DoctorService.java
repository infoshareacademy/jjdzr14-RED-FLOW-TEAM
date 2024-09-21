package pl.infoshare.clinicweb.doctor;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientDto;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public void addDoctor(Doctor user) {

        doctorRepository.save(user);
    }

    public Optional<DoctorDto> findById(long id) {

        return doctorRepository.findById(id)
                .stream()
                .map(doctorMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Doctor not found with %s", id)));
    }


    public List<Optional<DoctorDto>> findAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<DoctorDto> findPage(int pageNumber) {

        final int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id"));
        Page<Doctor> entities = doctorRepository.findAll(pageable);

        Page<DoctorDto> doctors = entities.map(doctor -> {
            DoctorDto doctorDto = doctorMapper.toDto(doctor).get();

            return doctorDto;
        });

        return doctors;
    }

    public void deleteDoctor(Long idDoctor) {

        doctorRepository.deleteById(idDoctor);
    }

    public void updateDoctor(DoctorDto doctorDto, Address address) {

        Doctor doctor = doctorMapper.toEntity(doctorDto);
        doctor.setAddress(address);

        doctorRepository.save(doctor);
    }

    public void findDoctorByKey(String name, String surname) {

    }


    List<Optional<DoctorDto>> findDoctorBySpecialization(Specialization specialization) {

        return doctorRepository.findAll()
                .stream()
                .filter(doctor -> doctor.getSpecialization().equals(specialization))
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public void setDoctorAttributes(Doctor doctor, PersonDetails personDetails,
                                    Address address, Specialization specialization) {

        doctor.setDetails(personDetails);
        doctor.setAddress(address);
        doctor.setSpecialization(specialization);


    }


}
