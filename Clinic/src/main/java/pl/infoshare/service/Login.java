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
    private static String currentlyCheckedLogin;


    public static void loginMenu() {

        int userChoice = chooseLoginOption();

        switch (userChoice) {

            case 1:

                getDataFromJsonUser(PatientService.PATIENT_PATHJSON);
                System.out.println("Logowanie pacjenta. ");

                tryLoginUser();

                break;

            case 2:

                getDataFromJsonUser(DoctorService.DOCTOR_PATH);
                System.out.println("Logowanie lekarza. ");

                tryLoginUser();

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

    public static void tryLoginUser() {

        provideUserLogin();
        provideUserPassword();


    }

    private static void provideUserLogin() {

        String login = "";

        do {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Podaj login: ");
            login = scanner.nextLine();

            if (!validProvidedLogin(login)) {

                System.out.println("Nie ma takiego uzytkownika. ");
            }

        } while (!validProvidedLogin(login));

    }

    private static void provideUserPassword() {

        String password = "";

        do {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Podaj haslo: ");
            password = scanner.nextLine();

            if (isEnteredPasswordCorrect(password)) {

                System.out.println("ZALOGOWANO UZYTKOWNIKA. ");

            } else {
                System.out.println("Bledne haslo. ");
            }

        } while (!isEnteredPasswordCorrect(password));
    }


    private static boolean isEnteredPasswordCorrect(String password) {

        return password.equals(userData.get(currentlyCheckedLogin));


    }

    private static boolean validProvidedLogin(String login) {


        for (String key : userData.keySet()) {

            if (login.equals(key)) {

                currentlyCheckedLogin = login;
                return true;

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
