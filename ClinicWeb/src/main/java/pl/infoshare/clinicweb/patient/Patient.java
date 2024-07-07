package pl.infoshare.clinicweb.patient;

import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.user.PersonDetails;

public class Patient {

    private PersonDetails personDetails;
    private DoctorDto doctor;
    private Address address;
    boolean hasInsurance;

    public Patient() {

    }

    public Patient(PersonDetails patientDetails, Address patientAddress) {

        this.personDetails = patientDetails;
        this.address = patientAddress;
        this.hasInsurance = false;
    }


    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDto doctor) {
        this.doctor = doctor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "personDetails=" + personDetails +
                ", doctor=" + doctor +
                ", address=" + address +
                ", hasInsurance=" + hasInsurance +
                '}';
    }
}
