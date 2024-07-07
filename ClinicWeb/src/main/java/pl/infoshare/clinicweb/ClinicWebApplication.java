package pl.infoshare.clinicweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClinicWebApplication.class, args);


        String path = "ClinicWeb/src/main/resources/doctors.json";
        String path2 = "ClinicWeb/src/main/resources/patients.json";

        PersonDetails personDetails = new PersonDetails("Damian", "Korzen", "444-444-333", "7564932323", LocalDate.of(1975, 12, 12));
        Address address = new Address();
        address.setCity("Lodz");
        address.setStreet("Warszawska");
        address.setHouseNumber("8");
        address.setFlatNumber("76");
        address.setCountry("Poland");
        address.setZipCode("30-521");
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName("Adam");
        doctorDto.setSurname("Balwan");
        doctorDto.setSpecialization(Specialization.CHIRURGY.getDescription());
        Patient patient = new Patient(personDetails, address);
        patient.setHasInsurance(true);
        patient.setDoctor(doctorDto);

        FileService fs = new FileService(new ObjectMapper());
        fs.writeToFile(patient, path2);




    }

}
