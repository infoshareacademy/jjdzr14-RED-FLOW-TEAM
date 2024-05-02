package pl.infoshare.service;

public class SurnameValidator {


    public static boolean isSurnameValid(String surname) {

        return isLengthValid(surname) && isFormatValid(surname);
    }

    public static boolean isLengthValid(String surname) {

        return surname.length() > 1 && surname.length() < 20;

    }

    public static boolean isFormatValid(String surname) {
        final String REGEX = "[a-zA-Z]+";

        return surname.matches(REGEX);

    }
}
