package pl.infoshare.clinicweb.patient;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.user.PersonDetails;

public class PatientDto {

    @NotEmpty(message = "Pole nie może być puste.")
    @Size(min = 2, max = 20, message = "Pole musi zawierać od 2 do 20 znaków.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Pole musi składać się z samych liter")
    private String name;

    @NotEmpty(message = "Pole nie może być puste.")
    @Size(min = 2, max = 20, message = "Pole musi zawierać od 2 do 20 znaków.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Pole musi składać się z samych liter")
    private String surname;

    @NotNull(message = "pole nie może być puste")
    private String phoneNumber;

    @NotEmpty(message = "Pole nie może być puste.")
    @Pattern(regexp = "[0-9]{11}", message = "Pole musi zawierać 11 cyfr. ")
    private String pesel;
    private DoctorDto doctor;

    public PatientDto(PersonDetails personDetails) {
    }

    public PatientDto() {
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

    public DoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDto doctor) {
        this.doctor = doctor;
    }

}
