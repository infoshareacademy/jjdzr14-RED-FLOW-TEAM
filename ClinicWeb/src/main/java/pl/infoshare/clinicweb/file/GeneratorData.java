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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GeneratorData {
    static PeselGeneratorParams.Gender gender = PeselGeneratorParams.Gender.FEMALE;

    String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";
    String DOCTOR_PATH = "ClinicWeb/src/main/resources/doctors.json";

    Specialization[] specializations = Specialization.values();
    List<Specialization> list = Arrays.stream(specializations).toList();

    Random rand = new Random();
    Faker faker = new Faker(new Locale("pl"));


    public void writeRandomObjects(int count, Object object) {
        ObjectMapper mapper = new ObjectMapper();
        FileService fileService = new FileService(mapper);
        for (int i = 0; i < count; i++) {
            if (object instanceof Patient) {
                Object o = generateRandomObject(object);
                fileService.writeToFile(o, PATIENT_PATH);
            } else if (object instanceof Doctor) {
                Object doctor = generateRandomObject(object);
                fileService.writeToFile(doctor, DOCTOR_PATH);
            }

        }

    }

    private Object generateRandomObject(Object object) {
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
        if (object instanceof Patient) {
            Patient p = new Patient();
            p.setPersonDetails(new PersonDetails(firstName, surname, phoneNumber, generatedPesel, birthDate, genderName));
            p.setAddress(new Address(city, flatNumber, houseNumber, street, zip, country));
            return p;
        } else {
            Doctor doctor = new Doctor();
            Specialization specialization = list.get(rand.nextInt(list.size()));
            doctor.setPersonDetails(new PersonDetails(firstName, surname, phoneNumber, generatedPesel, birthDate, genderName));
            doctor.setAddress(new Address(city, flatNumber, houseNumber, street, zip, country));
            doctor.setSpecialization(specialization.getDescription());
            return doctor;
        }
    }
}
//        }
//                }

//private Doctor generateRandomDoctor() {
//    String phoneNumber = faker.phoneNumber().phoneNumber();
//    String name = faker.name().firstName();
//    String surname = faker.name().lastName();
//    String generatedPesel = PeselGenerator.generatePeselStatic();
//    Pesel pesel = new Pesel(generatedPesel);
//    LocalDate birthDate = pesel.getBirthDate();
//    gender = PeselGeneratorParams.Gender.FEMALE;
//    Gender genderName = Gender.WOMAN;
//    String country = faker.address().country();
//    String zip = faker.address().zipCode();
//    String city = faker.address().city();
//    String street = faker.address().streetAddress();
//    String houseNumber = faker.address().buildingNumber();
//    String flatNumber = faker.address().streetAddressNumber();
//    PersonDetails personDetails = new PersonDetails(name, surname, phoneNumber, generatedPesel, birthDate, genderName);
//    Address address = new Address(country, zip, houseNumber, street, zip, country);
//    Doctor doctor = new Doctor();
//    doctor.setPersonDetails(new PersonDetails(name, surname, phoneNumber, generatedPesel, birthDate, genderName));
//    doctor.setAddress(new Address(city, flatNumber, houseNumber, street, zip, country));
//    Specialization specialization = list.get(rand.nextInt(list.size()));
//    doctor.setSpecialization(specialization.getDescription());
//    return doctor;

//}
//}
//}