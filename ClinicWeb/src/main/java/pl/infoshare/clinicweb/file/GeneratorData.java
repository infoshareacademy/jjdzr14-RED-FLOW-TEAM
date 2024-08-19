package pl.infoshare.clinicweb.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.github.viepovsky.polishutils.pesel.Pesel;
import io.github.viepovsky.polishutils.pesel.PeselGenerator;
import io.github.viepovsky.polishutils.pesel.PeselGeneratorParams;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.Gender;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;
import java.util.*;

public class GeneratorData {
    static PeselGeneratorParams.Gender gender = PeselGeneratorParams.Gender.FEMALE;

    String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";
    String DOCTOR_PATH = "ClinicWeb/src/main/resources/doctors.json";

    Specialization[] specializations = Specialization.values();
    List<Specialization> list = Arrays.stream(specializations).toList();

    Random rand = new Random();
    Faker faker = new Faker(new Locale("pl"));


    private PersonDetails generatePersonDetails() {
        String firstName = faker.name().firstName();
        String surname = faker.name().lastName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String generatedPesel = PeselGenerator.generatePeselStatic();
        Pesel pesel = new Pesel(generatedPesel);
        LocalDate birthDate = pesel.getBirthDate();
        gender = PeselGeneratorParams.Gender.FEMALE;
        Gender genderName = Gender.WOMAN;
        return new PersonDetails(firstName, surname, phoneNumber, generatedPesel, birthDate, genderName);
    }

    private Address generateAddress() {
        String city = faker.address().city();
        String country = faker.address().country();
        String zip = faker.address().zipCode();
        String street = faker.address().streetAddress();
        String houseNumber = faker.address().buildingNumber();
        String flatNumber = faker.address().streetAddressNumber();
        return new Address(city, flatNumber, houseNumber, street, zip, country);
    }


    public void writeRandomObjects(int count, Object object) {
        ObjectMapper mapper = new ObjectMapper();
        FileService fileService = new FileService(mapper);
        for (int i = 0; i < count; i++) {
            if (object instanceof Patient) {
                Object o = generateRandomPatient(object);
                fileService.writeToFile(o, PATIENT_PATH);
            } else if (object instanceof Doctor) {
                Object doctor = generateRandomDoctor(object);
                fileService.writeToFile(doctor, DOCTOR_PATH);
            }

        }

    }

    private Patient generateRandomPatient(Object object) {
        Patient patient = new Patient();
        if (object instanceof Patient) {
            patient.setPersonDetails(generatePersonDetails());
            patient.setAddress(generateAddress());
        } else throw new NoSuchElementException();
        return patient;
    }


    public Doctor generateRandomDoctor(Object o) {
        Doctor doctor = new Doctor();
        if (o instanceof Doctor) {
            Specialization specialization = list.get(rand.nextInt(list.size()));
            doctor.setPersonDetails(generatePersonDetails());
            doctor.setAddress(generateAddress());
            doctor.setSpecialization(specialization.getDescription());
        } else throw new NoSuchElementException();
        return doctor;

    }
}
