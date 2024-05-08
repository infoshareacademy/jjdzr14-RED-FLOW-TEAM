package pl.infoshare.model;
import java.time.LocalDate;
public class Patient {

    private Details details;
    private String pesel;
    private LocalDate birthDate;
    private int age;
    private Clinic clinic;
    private Doctor doctor;
    private User user;

    public Patient() {}

    public Patient(User user, Details details, String pesel, LocalDate birthDate, int age, Clinic clinic, Doctor doctor) {

        this.user = user;
        this.details = details;
        this.pesel = pesel;
        this.birthDate = birthDate;
        this.age = age;
        this.clinic = clinic;
        this.doctor = doctor;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "user='" + user +
                "details='" + details +
                ", pesel=" + pesel + '\'' +
                "', birthDate='" + birthDate +
                "', age='" + age +
                "', clinic='" + clinic +
                "', doctor='" + doctor +
                '}';
    }
}
