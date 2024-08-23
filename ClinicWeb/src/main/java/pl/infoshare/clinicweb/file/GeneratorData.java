package pl.infoshare.clinicweb.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.viepovsky.polishutils.pesel.Pesel;
import io.github.viepovsky.polishutils.pesel.PeselGenerator;
import io.github.viepovsky.polishutils.pesel.PeselGeneratorParams;
import net.datafaker.Faker;
import org.springframework.ui.Model;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.Gender;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.*;

public class GeneratorData {


    private static final int count = 15;

    static String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";
    static String DOCTOR_PATH = "ClinicWeb/src/main/resources/doctors.json";

    static Specialization[] specializations = Specialization.values();
    static List<Specialization> list = Arrays.stream(specializations).toList();
    static Random rand = new Random();

    public static PersonDetails generatePersonDetails() {
        Faker faker = new Faker(new Locale("pl"));
        PersonDetails personDetails = new PersonDetails();
        String generatedPesel = PeselGenerator.generatePeselStatic();
        Pesel pesel = new Pesel(generatedPesel);
        personDetails.setPesel(generatedPesel);
        personDetails.setName(generateFirstNameForPesel(generatedPesel, faker));
        personDetails.setSurname(faker.name().lastName());
        personDetails.setPhoneNumber(faker.phoneNumber().phoneNumber());
        personDetails.setBirthDate(pesel.getBirthDate());
        personDetails.setGender(Gender.valueOf(pesel.getGender()));
        return personDetails;
    }

    private static Address generateAddress() {
        Faker faker = new Faker(new Locale("pl"));
        Address address = new Address();
        address.setCity(faker.address().city());
        address.setCountry(faker.address().country());
        address.setZipCode(faker.address().zipCode());
        address.setStreet(faker.address().streetAddress());
        address.setHouseNumber(faker.address().buildingNumber());
        address.setFlatNumber(faker.address().streetAddressNumber());
        return address;
    }

    private static boolean isFemalePesel(String pesel) {
        int genderDigit = Character.getNumericValue(pesel.charAt(9));
        return genderDigit % 2 == 0;
    }

    private static String generateFirstNameForPesel(String pesel, Faker faker) {
        if (isFemalePesel(pesel)) {
            return faker.name().femaleFirstName();
        } else {
            return faker.name().malefirstName();
        }
    }

    private static void writeRandomObjects(Class<?> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        FileService fileService = new FileService(mapper);
        for (int i = 0; i < GeneratorData.count; i++) {
            if (clazz == Patient.class) {
                Patient patient = generateRandomPatient();
                fileService.writeToFile(patient, PATIENT_PATH);
            } else if (clazz == Doctor.class) {
                Doctor doctor = generateRandomDoctor();
                fileService.writeToFile(doctor, DOCTOR_PATH);
            }
        }
    }

    private static Patient generateRandomPatient() {
        Patient patient = new Patient();
        patient.setPersonDetails(generatePersonDetails());
        patient.setAddress(generateAddress());
        return patient;
    }

    private static Doctor generateRandomDoctor() {
        Doctor doctor = new Doctor();
        Specialization specialization = list.get(rand.nextInt(list.size()));
        doctor.setPersonDetails(generatePersonDetails());
        doctor.setAddress(generateAddress());
        doctor.setSpecialization(specialization.getDescription());
        return doctor;
    }

    public static void generateAndSaveData() {
        writeRandomObjects(Patient.class);
        writeRandomObjects(Doctor.class);
    }

    public static void generateAndSaveData(Model model) {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        writeRandomObjects(Patient.class);
        writeRandomObjects(Doctor.class);
        model.addAttribute("patient", patient);
        model.addAttribute("doctor", doctor);
    }
}
