package pl.infoshare.clinicweb.user;

import pl.infoshare.clinicweb.Utils;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.patient.Patient;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class PersonDetails {
    private String name;
    private String surname;
    private String phoneNumber;
    private long idNumber;
    private String pesel;
    private LocalDate birthDate;

    public PersonDetails() {

    }

    public PersonDetails(String name, String surname, long idNumber, String phoneNumber, String pesel, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.pesel = pesel;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person details{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber=" + idNumber +
                ", pesel=" + pesel +
                ", birtbDate=" + birthDate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber() {
        this.idNumber = generateID();
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

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

    private long generateID() {
        Date date = new Date();
        long IDNumber = date.getTime() % 10000;

        return IDNumber;
    }
}
