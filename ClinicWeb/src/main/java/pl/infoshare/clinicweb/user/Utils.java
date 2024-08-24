package pl.infoshare.clinicweb.user;

public class Utils {

    public static boolean hasPeselCorrectDigits(String pesel) {

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
}