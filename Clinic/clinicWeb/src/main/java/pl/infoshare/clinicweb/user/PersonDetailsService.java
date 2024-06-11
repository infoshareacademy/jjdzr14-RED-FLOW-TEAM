package pl.infoshare.clinicweb.user;

import pl.infoshare.clinicweb.Utils;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;

import java.util.Scanner;

public class PersonDetailsService {
    public static void addName(Object object) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Podaj imie: ");
            userInput = scanner.nextLine();

            if (!Utils.isLengthValid(userInput)) {

                System.out.println("Nieodpowiedni rozmiar pola. Pole musi zawierac od 2 do 20 znakow. ");

            } else if (!Utils.isFormatValid(userInput)) {

                System.out.println("Bledny format. To pole musi skladac sie wylacznie z liter.");

            } else if (Utils.isNameOrSurnameValid(userInput)) {

                if (object instanceof Patient) {
                    Patient patient = (Patient) object;

                    patient.getPersonDetails().setName(userInput);
                }

                if (object instanceof Doctor) {
                    Doctor doctor = (Doctor) object;

                    doctor.getPersonDetails().setName(userInput);

                }
            }

        } while (!Utils.isNameOrSurnameValid(userInput));
    }
    public static void addSurname(Object object) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Podaj nazwisko: ");
            userInput = scanner.nextLine();

            if (!Utils.isLengthValid(userInput)) {

                System.out.println("Nieodpowiedni rozmiar pola. Pole musi zawierac od 2 do 20 znakow. ");

            } else if (!Utils.isFormatValid(userInput)) {

                System.out.println("Bledny format. To pole musi skladac sie wylacznie z liter.");

            } else if (Utils.isNameOrSurnameValid(userInput)) {

                if (object instanceof Patient) {
                    Patient patient = (Patient) object;

                    patient.getPersonDetails().setSurname(userInput);
                }

                if (object instanceof Doctor) {
                    Doctor doctor = (Doctor) object;

                    doctor.getPersonDetails().setSurname(userInput);

                }
            }

        } while (!Utils.isNameOrSurnameValid(userInput));
    }

    public static void addPesel(Object object) {

        String userInput;
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println("Podaj pesel: ");
            userInput = scanner.nextLine();

            if (!Utils.isPeselValid(userInput)) {

                System.out.println("Nieprawidlowy numer pesel.");

            } else if (Utils.isPeselValid(userInput)) {

                if (object instanceof Patient) {
                    Patient patient = (Patient) object;

                    patient.getPersonDetails().setPesel(userInput);
                    patient.getPersonDetails().setPesel(userInput);

                    patient.getPersonDetails().setBirthDate(Utils.decodeDateOfBirth(patient));

                } else if (object instanceof Doctor) {

                    Doctor doctor = (Doctor) object;
                    doctor.getPersonDetails().setPesel(userInput);
                    doctor.getPersonDetails().setPesel(userInput);
                    doctor.getPersonDetails().setBirthDate(Utils.decodeDateOfBirth(doctor));
                }

            }

        } while (!Utils.isPeselValid(userInput));
    }


}
