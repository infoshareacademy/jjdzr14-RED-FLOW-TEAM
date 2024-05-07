package pl.infoshare.service;
import pl.infoshare.model.Details;
import pl.infoshare.model.Patient;

import java.util.Scanner;


public class PatientService {


    private static final String PATIENT_PATH = "Clinic/src/main/resources/patients.txt";


    public static void addPatient(Patient patient) {

        Scanner scanner = new Scanner(System.in);
        Details details = new Details();
        patient.setDetails(details);


        String userInput;


        System.out.println("Dodawanie pacjenta -> ");

        try {

            do {
                System.out.println("Podaj imie: ");
                userInput = scanner.nextLine();

                if (!Utils.isLengthValid(userInput)) {

                    System.out.println("Nieodpowiedni rozmiar pola. Pole musi zawierac od 2 do 20 znakow. ");

                } else if (!Utils.isFormatValid(userInput)) {

                    System.out.println("Bledny format. To pole musi skladac sie wylacznie z liter.");

                } else if (Utils.isNameOrSurnameValid(userInput)) {

                    patient.getDetails().setName(userInput);
                }

            } while (!Utils.isNameOrSurnameValid(userInput));


            do {

                System.out.println("Podaj nazwisko: ");
                userInput = scanner.nextLine();

                if (!Utils.isLengthValid(userInput)) {

                    System.out.println("Nieodpowiedni rozmiar pola. Pole musi zawierac od 2 do 20 znakow. ");

                } else if (!Utils.isFormatValid(userInput)) {

                    System.out.println("Bledny format. To pole musi skladac sie wylacznie z liter.");

                } else if (Utils.isNameOrSurnameValid(userInput)) {

                    patient.getDetails().setSurname(userInput);
                }


            } while (!Utils.isNameOrSurnameValid(userInput));


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


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");

        }

        FileService.writeToFile(patient, PATIENT_PATH);

    }


}

