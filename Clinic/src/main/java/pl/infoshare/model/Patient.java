package pl.infoshare.model;

import java.util.List;

public class Patient {

    private String birthDate;
    Details details;
    private Clinic clinic;

    public Patient () {

    }

    public Patient(String birthDate,Details details) {

        this.birthDate = birthDate;
        this.details = details;

    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "details=" + details +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
