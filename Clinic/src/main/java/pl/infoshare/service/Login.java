package pl.infoshare.service;

import java.util.HashMap;
import java.util.Scanner;

import static pl.infoshare.service.FileService.*;

public class Login {


    protected static HashMap<String, String> userData = new HashMap<>();
    private static String currentlyCheckedLogin;


    public static void loginMenu() {

        int userChoice = chooseLoginOption();

        switch (userChoice) {

            case 1:

                getDataFromJsonUser(PATIENT_PATH);
                System.out.println("Logowanie pacjenta. ");

                tryLoginUser();

                break;

            case 2:

                getDataFromJsonUser(DOCTOR_PATH);
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


}
