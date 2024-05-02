package pl.infoshare.service;
import pl.infoshare.model.Patient;
import java.time.LocalDate;

public class BirthDateDecoder {


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
}
