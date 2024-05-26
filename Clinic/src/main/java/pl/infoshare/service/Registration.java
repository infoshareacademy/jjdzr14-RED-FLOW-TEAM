package pl.infoshare.service;

import pl.infoshare.model.Role;
import pl.infoshare.model.User;

import java.util.Scanner;


public class Registration {


    public static void registerUser() {

        try {

            System.out.println("REJESTRACJA UZYTKOWNIKA ");

            User user = new User(createLogin(), createPassword(), addEmail(), chooseRole());

            if (user.getRole().equals(Role.PATIENT)) {
                PatientService.addPatient(user);

            } else if (user.getRole().equals(Role.DOCTOR)) {
                DoctorService.addDoctor(user);
            } else {
                System.out.println("ADMIN");
            }


        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String createLogin() {

        String login = "";
        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Podaj nazwe uzytkownika: ");
            userInput = scanner.nextLine();

            if (Utils.isLoginValid(userInput)) {

                login = userInput;
                break;

            } else if (Utils.isUsernameExisting(userInput)) {
                System.out.println("Ta nazwa uzytkownika jest juz zajeta.");
            }

        } while (!Utils.isLoginValid(userInput));

        return login;

    }

    private static String createPassword() {

        String password = "";
        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Podaj haslo. Haslo musi skladac sie z od 8 do 16 znakow, w tym z przynajmniej 1 duzej litery, 1 malej litery, 1 cyfry i 1 znaku specjalnego ");
            userInput = scanner.nextLine();

            if (Utils.isPasswordValid(userInput)) {
                password = userInput;
                break;

            }

        } while (!Utils.isPasswordValid(userInput));

        return password;
    }

    private static Role chooseRole() {

        int userRoleChoice = 0;
        Role role = null;
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("Rejestruje sie jako: (wprowadz odpowiednia cyfre)\n" +
                    "1.Pacjent \n" +
                    "2.Lekarz \n" +
                    "3.Admin \n" +
                    "0.Wyjscie"
            );

            userRoleChoice = scanner.nextInt();

            switch (userRoleChoice) {

                case 0:
                    System.out.println("Wyjscie");
                    break;
                case 1:
                    role = Role.PATIENT;
                    break;
                case 2:
                    role = Role.DOCTOR;
                    break;
                case 3:
                    role = Role.ADMIN;
                    break;
                default:
                    System.out.println("Invalid role");
                    break;
            }
        } while (role == null);

        return role;
    }

    private static String addEmail() {

        String email = "";
        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Podaj adres email: ");
            userInput = scanner.nextLine();

            if (Utils.isEmailValid(userInput)) {

                email = userInput;
                break;

            }
        } while (!Utils.isEmailValid(userInput));

        return email;
    }

}


