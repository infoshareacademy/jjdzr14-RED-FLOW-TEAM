package pl.infoshare.model;

public class Patient {

    Details details;
    private int age;
    private String pesel;
    private Clinic clinic;
    Address address;
    Doctor doctor;


    public Patient(Details details, int age, String pesel, Clinic clinic, Address address, Doctor doctor) {
        this.details = details;
        this.age = age;
        this.pesel = pesel;
        this.clinic = clinic;
        this.address = address;
        this.doctor = doctor;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient " +
                " " + age + " l " +
                "  pesel = " + pesel + '\'' +
                ", clinic = " + clinic +
                ", address = " + address +
                ", doctor = " + doctor + " ";
    }
}
