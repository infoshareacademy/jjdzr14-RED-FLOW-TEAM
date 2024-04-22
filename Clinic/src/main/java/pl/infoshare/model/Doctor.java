package pl.infoshare.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doctor {

    private String specialization;
    private List <Patient> patient;
    private Clinic clinic;
    private boolean online;
    private boolean availability;
    public Details details;

    public Doctor() {
    }

    public Doctor(String specialization, boolean online, boolean availability, Details details) {
        this.specialization = specialization;
        this.online = online;
        this.availability = availability;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization='" + specialization + '\'' +
                ", clinic=" + clinic +
                ", online=" + online +
                ", availability=" + availability +
                ", details=" + details +
                ", patient=" + patient +
                '}';
    }

    List <Doctor> doctorTest = new ArrayList<>();

// do DoctorService

    public static void printDoctors() {

        Details details =  new Details("Jan", "Kowalski","123456789", "L999");
        Doctor doctor = new Doctor("Onkologia", true, true, details);
        List<Doctor> doctorTest = new ArrayList<>();

        doctorTest.add(doctor);

        for(Doctor doctor1:doctorTest){
            System.out.println(doctor1);
        }

    }




}
