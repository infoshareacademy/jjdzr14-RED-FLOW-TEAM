package pl.infoshare.service;
import java.time.LocalDate;
import java.time.Period;

public class AgeCounter {

    public static int countAge(LocalDate birthDate) {

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        return age;
    }

}
