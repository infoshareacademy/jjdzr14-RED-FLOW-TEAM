package pl.infoshare.service;
import pl.infoshare.model.Details;
import pl.infoshare.model.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientService {


    private static final String PATIENT_PATH = "Clinic/src/main/resources/patients.txt";


    public static void addPatient (Patient patient) {

        Details details = new Details();
        patient.setDetails(details);

        List <Patient> patientsList = new ArrayList<>();

        final String regex = "[a-zA-Z]+";

        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("Dodawanie pacjenta -> ");

        do {

            System.out.println("Podaj imie: ");
            userInput = scanner.nextLine();

            if (userInput.length() < 1) {

                System.out.println("Pole imie nie moze byc puste.");

            } else if (!userInput.matches(regex)) {

                System.out.println("Bledny format. Imie powinno skladac sie z samych liter.");

            } else {

                patient.getDetails().setName(userInput);
            }

        } while (!userInput.matches(regex));


        do {
            System.out.println("Podaj nazwisko: ");
            userInput = scanner.nextLine();

            if (userInput.length() < 1) {

                System.out.println("Pole nazwisko nie moze byc puste.");

            } else if (!userInput.matches(regex)) {

                System.out.println("Bledny format. Nazwisko powinno skladac sie z samych liter.");

            } else {

                patient.getDetails().setSurname(userInput);
            }
        } while (!userInput.matches(regex));


        patientsList.add(patient);
        FileService.writeToFile(patient, PATIENT_PATH);

    }


}
