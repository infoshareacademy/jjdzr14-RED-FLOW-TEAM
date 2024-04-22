package pl.infoshare.service;
import pl.infoshare.model.Details;
import pl.infoshare.model.Patient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientService {

    private static String patientName;
    private static String patientSurname;
    private static String patientID;
    private static String patientPhoneNumber;
    private static String patientBirthDate;
    private static List <Patient> patientsList = new ArrayList<>();
    private static String fileOfPatientsPath = "Clinic/src/main/resources/patients.txt";


    public static List<Patient> getPatients() {

        return patientsList;

    }

    public static void addPatient () {

        final String regex = "[a-zA-Z]+";
//        final String regexNo = "\\d+";

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // full name to be added to text file of Patients
        String fullName;

        System.out.println("Dodawanie pacjenta -> ");

        //patient name
        do {

            System.out.println("Podaj imie: ");
            userInput = scanner.nextLine();

            if (userInput.length() < 1) {

                System.out.println("Pole imie nie moze byc puste.");

            } else if (!userInput.matches(regex)) {

                System.out.println("Bledny format. Imie powinno skladac sie z samych liter.");

            } else {

                patientName = userInput;
            }

        } while (!userInput.matches(regex));


        // patient surname
        do {
            System.out.println("Podaj nazwisko: ");
            userInput = scanner.nextLine();

            if (userInput.length() < 1) {

                System.out.println("Pole nazwisko nie moze byc puste.");

            } else if (!userInput.matches(regex)) {

                System.out.println("Bledny format. Nazwisko powinno skladac sie z samych liter.");

            } else {

                patientSurname = userInput;
            }
        } while (!userInput.matches(regex));


        fullName = patientName + " " + patientSurname;

        Details details = new Details(patientName, patientSurname, patientPhoneNumber, patientID);
        Patient patient = new Patient(patientBirthDate, details);

        PatientService.patientsList.add(patient);

        // test for fullName
        System.out.println(fullName);


        for (Patient p1 : patientsList) {
            System.out.println(p1);
        }

        FileService.writeToFile(fullName, fileOfPatientsPath);





    }
}
