package pl.infoshare.clinicweb.patient;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Address {

    @NotEmpty(message = "Pole nie może być puste")
    private String city;
    @NotEmpty(message = "Pole nie może być puste")
    private String country;
    @NotEmpty(message = "Pole nie może być puste")
    private String zipCode;
    @NotEmpty(message = "Pole nie może być puste")
    private String street;
    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "[0-9]{1,6}", message = "Pole musi zawierać same cyfry. ")
    private String houseNumber;
    @Pattern(regexp = "[0-9]{0,6}", message = "Pole musi zawierać same cyfry. ")
    private String flatNumber;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }


    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                '}';
    }
}
