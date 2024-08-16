package pl.infoshare.clinicweb.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.github.viepovsky.polishutils.pesel.Pesel;
import io.github.viepovsky.polishutils.pesel.PeselGenerator;
import io.github.viepovsky.polishutils.pesel.PeselGeneratorParams;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.Gender;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeneratorData {
    static PeselGeneratorParams.Gender gender = PeselGeneratorParams.Gender.FEMALE;
    String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";


    Faker faker = new Faker(new Locale("pl"));

    public Patient generateRandomPatient() {

        String firstName = faker.name().firstName();
        String surname = faker.name().lastName();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String generatedPesel = PeselGenerator.generatePeselStatic();
        Pesel pesel = new Pesel(generatedPesel);
        LocalDate birthDate = pesel.getBirthDate();
        gender = PeselGeneratorParams.Gender.FEMALE;
        Gender genderName = Gender.WOMAN;
        String city = faker.address().city();
        String country = faker.address().country();
        String zip = faker.address().zipCode();
        String street = faker.address().streetAddress();
        String houseNumber = faker.address().buildingNumber();
        String flatNumber = faker.address().streetAddressNumber();
        Patient p = new Patient();
        p.setPersonDetails(new PersonDetails(firstName, surname, phoneNumber, generatedPesel, birthDate, genderName));
        p.setAddress(new Address(city, flatNumber, houseNumber, street, zip, country));
        return p;
    }

    public void writeRandomPatients(int numberOfPatients) {
        ObjectMapper mapper = new ObjectMapper();
        FileService fileService = new FileService(mapper);
        for (int i = 0; i < numberOfPatients; i++) {
            Patient patient = generateRandomPatient();
            fileService.writeToFile(patient, PATIENT_PATH);
        }
    }
}