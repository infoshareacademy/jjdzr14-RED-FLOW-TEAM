package pl.infoshare.model;

public class Patient {

    Details details;
    String pesel;
    private int age;
    private Clinic clinic;
    private Doctor doctor;
   ;


    public Patient () {

    }

    public Patient(Details details, String pesel, int age, Clinic clinic, Doctor doctor) {

        this.details = details;
        this.pesel = pesel;
        this.age = age;
        this.clinic = clinic;
        this.doctor = doctor;


    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setBirthDate(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "details=" + details + '\'' +
                ", age=" + age +
                ", clinic=" + clinic +
                ", doctor=" + doctor +
                ", pesel=" + pesel +
                '}';
    }
}
