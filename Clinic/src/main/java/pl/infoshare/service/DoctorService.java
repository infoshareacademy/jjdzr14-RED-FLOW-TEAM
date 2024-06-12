package pl.infoshare.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.infoshare.model.Address;
import pl.infoshare.model.Doctor;
import pl.infoshare.model.PersonDetails;
import pl.infoshare.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.infoshare.service.FileService.DOCTOR_PATH;


public class DoctorService {


    public static void addDoctor(User user) {

        PersonDetails details = new PersonDetails();
        Address address = new Address();
        Doctor doctor = new Doctor();
        doctor.setPersonDetails(details);
        doctor.setAddress(address);
        doctor.setUser(user);

        try {

            System.out.println("DODAWANIE LEKARZA ");

            PersonDetails.addName(doctor);
            PersonDetails.addSurname(doctor);
            PersonDetails.addPesel(doctor);
            doctor.getPersonDetails().setIdNumber();

            FileService.writeToFile(doctor, DOCTOR_PATH);


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }


    }

    public static List<String> filterBySpecialization(String searchedSpecialization) {

        JSONArray jsonArray = FileService.convertFileToJSON(DOCTOR_PATH);
        ObjectMapper mapper = new ObjectMapper();


        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        List<Doctor> doctors = new ArrayList<>();


        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Doctor doctor = (Doctor) mapper.convertValue(jsonObject, Doctor.class);
            doctors.add(doctor);

        }

        List<String> filteredDoctors = doctors
                .stream()
                .filter(doctor -> doctor.getSpecialization().equalsIgnoreCase(searchedSpecialization))

                .map(doctor -> doctor.getPersonDetails().getName() + " " + doctor.getPersonDetails().getSurname() +
                        ", Specjalizacja: " + doctor.getSpecialization() +", Numer telefonu: " +
                        doctor.getPersonDetails().getPhoneNumber() + ", Dostepnosc: " +
                        doctor.hasAvailability() + ", Wizyta online: " + doctor.isOnline())

                .collect(Collectors.toList());

        if(filteredDoctors.size() < 1) {

            System.out.println("Nie znaleziono lekarza o takiej specjalizacji.");
        }


        return filteredDoctors;

    }
}



