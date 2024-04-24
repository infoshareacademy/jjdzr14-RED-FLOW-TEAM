package pl.infoshare.model;

import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private String address;
    private String openHours;
    private String doctorList;
    private String patientList;


    public Clinic(String addres, String openHours, String doctor, String patient) {
        this.address = addres;
        this.openHours = openHours;
        this.doctorList = doctor;
        this.patientList = patient;
    }


    public String getAddres() {
        return address;
    }

    public String getOpenHours() {
        return openHours;
    }

    public String getDoctor() {
        return doctorList;
    }

    public String getPatient() {
        return patientList;
    }


    @Override
    public String toString() {
        return "Clinic{" +
                "address = " + address +
                "openHours = " + openHours +
                "doctors = " + doctorList +
                "patients = " + patientList +
                "}";
    }

    public static void printClinic() {
        Clinic clinic = new Clinic("Gdańsk ul.Główna 1" + ", ", "8:00-20:00" + ", ", "Jak Konieczny" + ", ", "Mariusz Mostowiak");
        List<Clinic> clinicList = new ArrayList<>();
        clinicList.add(clinic);

        for (Clinic clinic1 : clinicList) {
            System.out.println(clinic1);
        }
    }

}
