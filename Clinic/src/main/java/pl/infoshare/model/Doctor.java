package pl.infoshare.model;
import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private User user;
    private String specialization;
    private List<Patient> patient;
    private Clinic clinic;
    private boolean online;
    private boolean availability;
    public Details details;



    public Doctor() {
    }

    public Doctor(User user, Details details, String specialization, boolean online, boolean availability) {

        this.user = user;
        this.details = details;
        this.specialization = specialization;
        this.online = online;
        this.availability = availability;

    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "user=" + user +
                ", specialization='" + specialization + '\'' +
                ", patient=" + patient +
                ", clinic=" + clinic +
                ", online=" + online +
                ", availability=" + availability +
                ", details=" + details +
                '}';
    }

    public static void printDoctors() {

        Details details =  new Details("Jan", "Kowalski","123456789", "L999");
        User user = new User();
        Doctor doctor = new Doctor(user, details, "Onkologia", true, true);
        List<Doctor> doctorTest = new ArrayList<>();

        doctorTest.add(doctor);

        for (Doctor doctor1 : doctorTest) {
            System.out.println(doctor1);
        }

    }


}
