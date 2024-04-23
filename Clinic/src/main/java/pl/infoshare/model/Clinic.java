package pl.infoshare.model;

import java.util.List;

public class Clinic {
    private String addres;
    private String openHours;
    private List<Doctor> doctorList;
    private List<Patient> patientList;


    public Clinic(String addres, int openHours, Doctor doctor, Patient patient) {
        this.addres = addres;
        this.openHours = openHours;
        this.doctorList = doctor;
        this.patientList = patient;
    }

    public String getAddres() {
        return addres;
    }

    public int getOpenHours() {
        return openHours;
    }

    public Doctor getDoctor() {
        return doctorList;
    }

    public Patient getPatient() {
        return patientList;
    }
    @Override
    public String toString() {
        return "Clinic{" +
                "addres = " + addres +
                "openHours = " + openHours +
                "doctors = " + doctorList +
                "patients = " + patientList +
                "}";
    }

}
