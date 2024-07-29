package pl.infoshare.clinicweb.user;

import java.time.LocalDate;

public class PersonDetails {

    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate birthDate;
    private String pesel;
    private Gender gender;

    public PersonDetails() {
    }

    public PersonDetails(String name, String surname, String phoneNumber, String pesel, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public PersonDetails(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "PersonDetails{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", pesel='" + pesel + '\'' +
                ", gender=" + gender +
                '}';
    }
}
