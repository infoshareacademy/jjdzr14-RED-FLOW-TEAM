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


    private long generateID() {
        Date date = new Date();
        long IDNumber = date.getTime() % 10000;

        return IDNumber;
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

}
