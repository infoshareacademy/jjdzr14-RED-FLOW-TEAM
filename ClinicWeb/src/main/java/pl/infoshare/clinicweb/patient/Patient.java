package pl.infoshare.clinicweb.patient;

import pl.infoshare.clinicweb.clinic.Clinic;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;

public class Patient {

    private PersonDetails personDetails;
    private Clinic clinic;
    private Doctor doctor;
    private Address address;

    public Patient() {

    }

    public Patient(PersonDetails patientDetails, Address patientAddress) {

        this.personDetails = patientDetails;
        this.address = patientAddress;
    }


    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }


    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDateOfBirth(Patient patient) {
        String pesel = personDetails.getPesel();
        String decodedYearPart = "";
        String secondYearPart = pesel.substring(0, 2);
        String month = pesel.substring(2, 4);
        int day = Integer.parseInt(pesel.substring(4, 6));

        if (month.startsWith("8") || month.startsWith("9")) {
            decodedYearPart = "18";
        } else if (month.startsWith("0") || month.startsWith("1")) {
            decodedYearPart = "19";
        } else if (month.startsWith("2") || month.startsWith("3")) {
            decodedYearPart = "20";
        } else if (month.startsWith("4") || month.startsWith("5")) {
            decodedYearPart = "21";
        } else {
            decodedYearPart = "22";
        }

        int decodedMonthPartBeginning = Character.getNumericValue(month.charAt(0)) % 2;
        String decodedMonthPart = Integer.toString(decodedMonthPartBeginning) + month.charAt(1);

        int fullYear = Integer.parseInt(decodedYearPart + secondYearPart);


        LocalDate dateOfBirth = LocalDate.of(fullYear, Integer.parseInt(decodedMonthPart), day);
        patient.personDetails.setBirthDate(dateOfBirth);
    }

    @Override
    public String toString() {
        return "Patient{" +
                ", personDetails=" + personDetails +
                ", clinic=" + clinic +
                ", doctor=" + doctor +
                ", address=" + address +
                '}';
    }
}
