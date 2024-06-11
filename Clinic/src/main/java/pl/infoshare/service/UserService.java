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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.infoshare.model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static pl.infoshare.service.FileService.*;

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
=======
    public static void loginMenu() {

        int userChoice = chooseLoginOption();


        switch (userChoice) {

            case 1:

                patientLoginMenu();

                break;

            case 2:

                doctorLoginMenu();

                break;

        }
    }

    private static void patientLoginMenu() {

        System.out.println("Logowanie pacjenta. ");
        User patient;
        String login;
        String password = "";

        do {
            login = provideUserLogin();
            patient = findUser(login, PATIENT_PATH);

            if (patient != null) {
                password = provideUserPassword();

                if (!patient.getPassword().equals(password)) {
                    displayInvalidPasswordMessage();
                } else {
                    displayCorrectlyLoggedInMessage();
                }
            }

        } while (!verifyCredentials(patient, login, password));
    }


    private static void doctorLoginMenu() {

        System.out.println("Logowanie lekarza. ");
        User doctor;
        String login;
        String password = "";

        do {
            login = provideUserLogin();
            doctor = findUser(login, DOCTOR_PATH);
            if (doctor != null) {
                password = provideUserPassword();

                if (!doctor.getPassword().equals(password)) {
                    displayInvalidPasswordMessage();
                } else {
                    displayCorrectlyLoggedInMessage();
                }
            }


        } while (!verifyCredentials(doctor, login, password));

    }

    private static void displayCorrectlyLoggedInMessage() {
        System.out.println("Zalogowano uzytkownika. ");
    }

    private static void displayInvalidPasswordMessage() {
        System.out.println("Nieprawidlowe haslo. ");
    }

    private static void displayInvalidUserMessage() {
        System.out.println("Nieprawidlowa nazwa uzytkownika. ");
    }

    public static boolean verifyCredentials(User user, String login, String password) {

        if (verifyUserLogin(user, login)) return verifyUserPassword(user, password);
        else return false;

    }

    private static boolean verifyUserPassword(User user, String password) {

        if (user == null) {
            displayInvalidPasswordMessage();
            return false;
        }

        return Objects.equals(user.getPassword(), password);

    }

    private static boolean verifyUserLogin(User user, String login) {

        if (user == null) {
            displayInvalidUserMessage();
            return false;
        }

        return Objects.equals(user.getLogin(), login);

    }

    public static User findUser(String login, String filename) {

        JSONArray jsonArray = (JSONArray) convertFileToJSON(filename);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject singlePerson = (JSONObject) jsonArray.get(i);

            User person = (User) mapper.convertValue(singlePerson.get("user"), User.class);

            users.add(person);

        }

        for (User u : users) {

            if (login.equals(u.getLogin())) {

                return u;
            }
        }


        return null;

    }

    private static String provideUserPassword() {

        String password = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj haslo: ");
        password = scanner.nextLine();

        return password;

    }

    private static String provideUserLogin() {

        String login = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj login: ");
        login = scanner.nextLine();


        return login;

    }

    public static int chooseLoginOption() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Logowanie jako:\n" +
                "1.Pacjent \n" +
                "2.Lekarz");

        int userChoice = scanner.nextInt();

        return userChoice;

    }

}
