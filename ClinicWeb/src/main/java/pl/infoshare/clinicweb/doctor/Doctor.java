package pl.infoshare.clinicweb.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor {

    private String specialization;
    private List<Patient> patient;
    private boolean online;
    private boolean availability;
    private PersonDetails details;
    private Address address;

    public Doctor() {
    }

    public Doctor(Address address, PersonDetails details) {
        this.address = address;
        this.details = details;
    }

    public Doctor(String specialization, List<Patient> patient, boolean online, boolean availability, PersonDetails details, Address address) {
        this.specialization = specialization;
        this.patient = patient;
        this.online = online;
        this.availability = availability;
        this.details = details;
        this.address = address;


    }

    public PersonDetails getPersonDetails() {
        return details;
    }

    public void setPersonDetails(PersonDetails details) {
        this.details = details;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDateOfBirth(Doctor doctor) {

        if (doctor.details.getPesel() == null) {
            throw new IllegalArgumentException("Pesel cannot be null");
        }

        String pesel = details.getPesel();
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
        doctor.details.setBirthDate(dateOfBirth);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization='" + specialization + '\'' +
                ", patient=" + patient +
                ", online=" + online +
                ", availability=" + availability +
                ", details=" + details +
                ", address=" + address +
                '}';
    }
}


