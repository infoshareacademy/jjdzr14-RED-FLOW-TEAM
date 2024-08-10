package pl.infoshare.clinicweb.patient;

import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.user.PersonDetails;

public class PatientDto {

    private String name;
    private String surname;
    private String phoneNumber;
    private String pesel;
    private DoctorDto doctor;

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
