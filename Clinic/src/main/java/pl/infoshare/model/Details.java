package pl.infoshare.model;

public class Details {

    private String name;
    private String surname;
    private String phoneNumber;
    private String idNumber;


    public Details () {

    }
    public Details(String name, String surname, String phoneNumber, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Details{" +
                "name='" + name + '\'' +
                ", surname='" + surname+ '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber=" + idNumber +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
