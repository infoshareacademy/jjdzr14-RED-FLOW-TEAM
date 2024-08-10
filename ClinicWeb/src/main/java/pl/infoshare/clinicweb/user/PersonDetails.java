package pl.infoshare.clinicweb.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PersonDetails {

    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 2, max = 20, message = "Pole musi zawierać od 2 do 20 znaków.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Pole musi składać się z samych liter")
    private String name;
    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 2, max = 20, message = "Pole musi zawierać od 2 do 20 znaków.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Pole musi składać się z samych liter")
    private String surname;
    @NotEmpty(message = "Pole nie może być puste")
    private String phoneNumber;
    private LocalDate birthDate;
    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "[0-9]{11}", message = "Pole musi zawierać 11 cyfr. ")
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
