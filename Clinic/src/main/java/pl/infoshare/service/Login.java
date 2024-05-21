package pl.infoshare.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

    private static HashMap<String, String> userData = new HashMap<>();


    public static void tryLoginUser() {

        Scanner scanner = new Scanner(System.in);

        int userChoice = chooseLoginOption();

        switch (userChoice) {

            case 1:

                tryLoginPatient();
                break;

            case 2:

                tryLoginDoctor();
                break;
        }

    }


    public static int chooseLoginOption() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Logowanie jako:\n" +
                "1.Pacjent \n" +
                "2.Lekarz");

        int userChoice = scanner.nextInt();

        return userChoice;

    }

    public static void tryLoginPatient() {


        do {

            System.out.println("Logowanie pacjenta: ");

            getDataFromJsonUser(PatientService.PATIENT_PATHJSON);

            provideLogin();
            providePassword();

            if (hasUserEnteredCorrectData(userData,
                    provideLogin(), providePassword())) {

                System.out.println("Zalogowano pacjenta. ");
                break;

            } else if (!hasUserEnteredCorrectData(userData, provideLogin(), providePassword())) {

                System.out.println("Bledne dane logowania. ");
            }

        } while (!hasUserEnteredCorrectData(userData,
                provideLogin(), providePassword()));
        ;

    }

    public static void tryLoginDoctor() {

        System.out.println("Logowanie lekarza: ");

        getDataFromJsonUser(DoctorService.DOCTOR_PATH);

        do {
            provideLogin();
            providePassword();

            if (hasUserEnteredCorrectData(userData, provideLogin(), providePassword())) {

                System.out.println("Zalogowano lekarza. ");

            } else {

                System.out.println("Bledne dane logowania. ");
            }

        } while (!hasUserEnteredCorrectData(userData,
                provideLogin(), providePassword()));

    }

    public static String provideLogin() {

        String login;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj login: ");
        login = scanner.next();

        return login;

    }

    public static String providePassword() {

        String password;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj haslo: ");
        password = scanner.next();

        return password;

    }


    public static boolean hasUserEnteredCorrectData(HashMap<String, String> userData, String login, String password) {

        String usrPassword = "";
        for (String key : userData.keySet()) {
            if (login.equals(key)) {
                usrPassword = userData.get(key);

            } else {
                System.out.println("Nieprawidlowa nazwa uzytkownika. ");
            }

            if (usrPassword.equals(password)) {

                return true;
            } else {

                System.out.println("Nieprawidlowe haslo. ");
            }
        }

        return false;
    }

    public static void getDataFromJsonUser(String filename) {

        JSONObject jsonObject = convertFileToJSON(filename);

        JSONObject user = (JSONObject) jsonObject.get("user");

        String login = (String) user.get("login");
        String password = (String) user.get("password");

        Login.userData.put(login, password);


    }

    public static JSONObject convertFileToJSON(String fileName) {


        JSONObject jsonObject = null;

        try {

            JSONParser parser = new JSONParser();

            jsonObject = (JSONObject) parser.parse(new FileReader(fileName));


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;

    }
}
