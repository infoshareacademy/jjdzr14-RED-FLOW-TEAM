package pl.infoshare.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.infoshare.model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static pl.infoshare.service.FileService.*;

public class UserService {


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
