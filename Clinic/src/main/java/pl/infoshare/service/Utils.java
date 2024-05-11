package pl.infoshare.service;

import pl.infoshare.model.Doctor;
import pl.infoshare.model.Patient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    private static final String PESEL_REGEX = "\\d{11}";
    public static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$";
    private static final String LETTER_REGEX = "[a-zA-Z]+";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isNameOrSurnameValid(String nameOrSurname) {

        return isLengthValid(nameOrSurname) && isFormatValid(nameOrSurname);
    }

    public static boolean isLengthValid(String nameOrSurname) {

        return nameOrSurname.length() > 1 && nameOrSurname.length() <= 20;

    }

    public static boolean isFormatValid(String nameOrSurname) {

        return nameOrSurname.matches(LETTER_REGEX);

    }

    public static boolean isPeselValid(String pesel) {

        return hasValidLength(pesel) && hasCorrectDigits(pesel) && hasCorrectFormat(pesel);
    }

    private static boolean hasValidLength(String pesel) {

        return pesel != null && pesel.length() == 11;
    }

    private static boolean hasCorrectFormat(String pesel) {

        return pesel.matches(PESEL_REGEX);
    }

    private static boolean hasCorrectDigits(String pesel) {

        int checkDigit = Character.getNumericValue(pesel.charAt(10));

        final int[] WEIGHTS = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * WEIGHTS[i];
        }

        int modulo = sum % 10;
        int calculatedDigits = 10 - modulo;

        return modulo == 0 || calculatedDigits == checkDigit;
    }

    public static LocalDate decodeDateOfBirth(Patient patient) {


        String decodedYearPart = "";
        String secondYearPart = patient.getPesel().substring(0, 2);
        String month = patient.getPesel().substring(2, 4);
        int day = Integer.parseInt(patient.getPesel().substring(4, 6));

        if (month.startsWith("8") || month.startsWith("9")) {
            decodedYearPart = "18";
        } else if (month.startsWith("0") || month.startsWith("1")) {
            decodedYearPart = "19";
        } else if (month.startsWith("2") || month.startsWith("3")) {
            decodedYearPart = "20";
        } else if (month.startsWith("4") || month.startsWith("5")) {
            decodedYearPart = "21";
        } else {
            decodedYearPart = "22";
        }

        int decodedMonthPartBeginning = Character.getNumericValue(month.charAt(0)) % 2;
        String decodedMonthPart = Integer.toString(decodedMonthPartBeginning) + month.charAt(1);

        int fullYear = Integer.parseInt(decodedYearPart + secondYearPart);


        LocalDate dateOfBirth = LocalDate.of(fullYear, Integer.parseInt(decodedMonthPart), day);


        return dateOfBirth;
    }

    public static int countAge(LocalDate birthDate) {

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        return age;
    }

    public static boolean isLoginValid(String userInput) {


        return (!isUsernameExisting(userInput)) && userInput.matches(LETTER_REGEX);
    }

    public static boolean isUsernameExisting(String userInput) {

        try {

            FileReader in = new FileReader(Registration.USERNAMES_FILE);
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(userInput))
                    return true;
            }
            in.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean isEmailValid(String userEmail) {

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String userPassword) {

        return userPassword.matches(PASSWORD_REGEX);
    }


    public static void addName(Object object) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Podaj imie: ");
            userInput = scanner.nextLine();

            if (!Utils.isLengthValid(userInput)) {

                System.out.println("Nieodpowiedni rozmiar pola. Pole musi zawierac od 2 do 20 znakow. ");

            } else if (!Utils.isFormatValid(userInput)) {

                System.out.println("Bledny format. To pole musi skladac sie wylacznie z liter.");

            } else if (Utils.isNameOrSurnameValid(userInput)) {

                if (object instanceof Patient) {
                    Patient patient = (Patient) object;

                    patient.getDetails().setName(userInput);
                }

                if (object instanceof Doctor) {
                    Doctor doctor = (Doctor) object;

                    doctor.getDetails().setName(userInput);

                }
            }

        } while (!Utils.isNameOrSurnameValid(userInput));
    }

    public static void addSurname(Object object) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Podaj nazwisko: ");
            userInput = scanner.nextLine();

            if (!Utils.isLengthValid(userInput)) {

                System.out.println("Nieodpowiedni rozmiar pola. Pole musi zawierac od 2 do 20 znakow. ");

            } else if (!Utils.isFormatValid(userInput)) {

                System.out.println("Bledny format. To pole musi skladac sie wylacznie z liter.");

            } else if (Utils.isNameOrSurnameValid(userInput)) {

                if (object instanceof Patient) {
                    Patient patient = (Patient) object;

                    patient.getDetails().setSurname(userInput);
                }

                if (object instanceof Doctor) {
                    Doctor doctor = (Doctor) object;

                    doctor.getDetails().setSurname(userInput);

                }
            }

        } while (!Utils.isNameOrSurnameValid(userInput));
    }


}