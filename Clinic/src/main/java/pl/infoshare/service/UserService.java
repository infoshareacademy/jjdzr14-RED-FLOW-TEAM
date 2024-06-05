package pl.infoshare.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.infoshare.model.PersonDetails;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class UserService {


    public static void searchBar() {

        System.out.println("Wprowadz imie: ");
        String name = getUserInput();

        System.out.println("Wprowadz nazwisko ");
        String surname = getUserInput();

        findDoctors(name, surname);

    }

    public static Set<PersonDetails> findDoctors(String name, String surname) {


        Set<PersonDetails> doctorsObjects = getDoctorsFromJson();

        Predicate<String> namePredicate = doctor -> doctor.equalsIgnoreCase(name);
        Predicate<String> surnamePredicate = doctor -> doctor.equalsIgnoreCase(surname);


        Set<PersonDetails> filteredStream = doctorsObjects.stream()

                .filter(doctor -> namePredicate.test(doctor.getName()))
                .filter(doctor -> surnamePredicate.test(doctor.getSurname()))

                .collect(Collectors.toSet());


        if (filteredStream.size() < 1) {

            System.out.println("Nie ma takiego lekarza. ");
        }

        return filteredStream;

    }


    private static Set<PersonDetails> getDoctorsFromJson() {

        JSONArray doctorsJsonArray = FileService.convertFileToJSON(FileService.DOCTOR_PATH);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        Set<PersonDetails> doctors = new HashSet<>();

        for (int i = 0; i < doctorsJsonArray.size(); i++) {


            JSONObject doctorObject = (JSONObject) doctorsJsonArray.get(i);

            JSONObject details = (JSONObject) doctorObject.get("personDetails");

            PersonDetails doctorDetails = (PersonDetails) mapper.convertValue(details, PersonDetails.class);

            doctors.add(doctorDetails);

        }

        return doctors;
    }

    private static String getUserInput() {

        String userInput;

        Scanner scanner = new Scanner(System.in);

        userInput = scanner.nextLine();


        return userInput;
    }
}
