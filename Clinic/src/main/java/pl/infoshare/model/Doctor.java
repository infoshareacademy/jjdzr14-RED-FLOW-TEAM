package pl.infoshare.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor {

    private String specialization;
    private List<Patient> patient;
    private Clinic clinic;
    private boolean online;
    private boolean availability;
    public Details details;

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Doctor() {
    }

    public Doctor(Details details, String specialization, boolean online, boolean availability) {
        this.details = details;
        this.specialization = specialization;
        this.online = online;
        this.availability = availability;

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

    @Override
    public String toString() {
        return getDetails().getName() + " " + details.getSurname() +
                "specjalizacja :" + specialization + '\'' +
                ", clinikaa :" + clinic +
                ", spotknia online : " + online +
                ", dostępność : " + availability +
                ", details : " + details +
                ", patient : " + patient +
                '}';
    }

    List<Doctor> doctorTest = new ArrayList<>();

// do DoctorService

    public static void printDoctors() {

        Details details = new Details("Jan", "Kowalski", "123456789", 999);
        Doctor doctor = new Doctor(details, "Onkologia", true, true);
        List<Doctor> doctorTest = new ArrayList<>();

        doctorTest.add(doctor);

        for (Doctor doctor1 : doctorTest) {
            System.out.println(doctor1);
        }

    }


}
