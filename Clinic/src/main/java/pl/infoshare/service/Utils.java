package pl.infoshare.service;
import pl.infoshare.model.Patient;
import java.time.LocalDate;
import java.time.Period;

public class Utils {

    public static boolean isNameOrSurnameValid(String nameOrSurname) {

        return isLengthValid(nameOrSurname) && isFormatValid(nameOrSurname);
    }

    public static boolean isLengthValid(String nameOrSurname) {

        return nameOrSurname.length() > 1 && nameOrSurname.length() <= 20;

    }

    public static boolean isFormatValid(String nameOrSurname) {
        final String REGEX = "[a-zA-Z]+";

        return nameOrSurname.matches(REGEX);

    }


    public static boolean isPeselValid(String pesel) {

        return hasValidLength(pesel) && hasCorrectDigits(pesel) && hasCorrectFormat(pesel);
    }

    public static boolean hasValidLength(String pesel) {

        return pesel != null && pesel.length() == 11;
    }

    public static boolean hasCorrectFormat(String pesel) {

        return pesel.matches("\\d+");
    }

    public static boolean hasCorrectDigits(String pesel) {

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
}