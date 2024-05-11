package pl.infoshare.service;

import pl.infoshare.model.Details;
import pl.infoshare.model.Patient;
import pl.infoshare.model.User;

import java.util.Scanner;


public class PatientService {


    private static final String PATIENT_PATH = "Clinic/src/main/resources/listPatient.txt";


    public static void addPatient(User user) {

        Patient patient = new Patient();
        Details details = new Details();

        patient.setDetails(details);
        patient.setUser(user);


        System.out.println("DODAWANIE PACJENTA");

        try {

            Utils.addName(patient);
            Utils.addSurname(patient);
            addPesel(patient);

            FileService.writeToFile(patient, PATIENT_PATH);


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }


    }

    private static void addPesel(Patient patient) {

        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("Podaj pesel: ");
            userInput = scanner.nextLine();

            if (!Utils.isPeselValid(userInput)) {

                System.out.println("Nieprawidlowy numer pesel.");

            } else if (Utils.isPeselValid(userInput)) {

                patient.setPesel(userInput);
                patient.setBirthDate(Utils.decodeDateOfBirth(patient));
                patient.setAge(Utils.countAge(Utils.decodeDateOfBirth(patient)));

            }

        } while (!Utils.isPeselValid(userInput));
    }


}

