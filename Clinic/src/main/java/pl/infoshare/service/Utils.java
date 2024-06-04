package pl.infoshare.service;

import pl.infoshare.model.Doctor;
import pl.infoshare.model.Patient;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    private static final String PESEL_REGEX = "\\d{11}";
    private static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$";
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

        return pesel.matches(PESEL_REGEX);
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

    public static LocalDate decodeDateOfBirth(Object object) {

        String pesel = "";

        if (object instanceof Patient) {
            Patient patient = (Patient) object;
            pesel = patient.getPersonDetails().getPesel();
        }

        if (object instanceof Doctor) {
            Doctor doctor = (Doctor) object;
            pesel = doctor.getPersonDetails().getPesel();
        }


        String decodedYearPart = "";
        String secondYearPart = pesel.substring(0, 2);
        String month = pesel.substring(2, 4);
        int day = Integer.parseInt(pesel.substring(4, 6));

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


    public static boolean isLoginValid(String userInput) {


        return (userInput.matches(LETTER_REGEX));
    }


    public static boolean isEmailValid(String userEmail) {

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String userPassword) {

        return userPassword.matches(PASSWORD_REGEX);
    }


}