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

                if (!NameValidator.isLengthValid(userInput)) {

                    System.out.println("Pole imie nie moze byc puste.");

                } else if (!NameValidator.isFormatValid(userInput)) {

                    System.out.println("Bledny format. Imie powinno skladac sie z samych liter.");

                } else {

                    patient.getDetails().setName(userInput);
                }

            } while (!NameValidator.isNameValid(userInput));


            do {

                System.out.println("Podaj nazwisko: ");
                userInput = scanner.nextLine();

                if (!SurnameValidator.isLengthValid(userInput)) {

                    System.out.println("Pole nazwisko nie moze byc puste.");

                } else if (!SurnameValidator.isFormatValid(userInput)) {

                    System.out.println("Bledny format. Nazwisko powinno skladac sie z samych liter.");

                } else {

                    patient.getDetails().setSurname(userInput);
                }

            } while (!SurnameValidator.isSurnameValid(userInput));


            do {

                System.out.println("Podaj pesel: ");
                userInput = scanner.nextLine();

                if (!PeselValidator.isPeselValid(userInput)) {

                    System.out.println("Nieprawidlowy numer pesel.");

                } else if (PeselValidator.isPeselValid(userInput)) {

                    patient.setPesel(userInput);
                    patient.setBirthDate(BirthDateDecoder.decodeDateOfBirth(patient));
                    patient.setAge(AgeCounter.countAge(BirthDateDecoder.decodeDateOfBirth(patient)));

                }

            } while (!PeselValidator.isPeselValid(userInput));


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");

        }

        FileService.writeToFile(patient, PATIENT_PATH);

    }


}

