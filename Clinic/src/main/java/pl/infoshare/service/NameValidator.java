package pl.infoshare.service;

public class NameValidator {


    public static boolean isNameValid(String name) {
        return isLengthValid(name) && isFormatValid(name);
    }

    public static boolean isLengthValid(String name) {

        return name.length() > 1 && name.length() < 20;
    }

    public static boolean isFormatValid(String name) {
        final String REGEX = "[a-zA-Z]+";

        return name.matches(REGEX);

    }
}
