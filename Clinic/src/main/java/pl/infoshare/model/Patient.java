package pl.infoshare.model;

public class Patient {

    private PersonDetails details;
    private Clinic clinic;
    private Doctor doctor;
    private User user;
    private Address address;

    public Patient() {
    }

    public Patient(User user, PersonDetails details, Address address, Clinic clinic, Doctor doctor) {

        this.user = user;
        this.details = details;
        this.address = address;
        this.clinic = clinic;
        this.doctor = doctor;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public PersonDetails getPersonDetails() {
        return details;
    }

    public void setPersonDetails(PersonDetails details) {
        this.details = details;
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


    @Override
    public String toString() {
        return "Patient{" +
                "user='" + user +
                "details='" + details +
                "', address='" + address +
                "', clinic='" + clinic +
                "', doctor='" + doctor +
                '}';
    }
}
