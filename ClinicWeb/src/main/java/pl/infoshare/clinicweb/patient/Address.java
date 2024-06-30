package pl.infoshare.clinicweb.patient;

public class Address {
    private String city;
    private String country;
    private String zipCode;
    private String street;

    public Address() {
    }

public Address(String city, String country, String zipCode, String street) {
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
    this.street = street;
}

public String getCity() {
    return city;
}

public void setCity(String city) {
    city = city;
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

@Override
public String toString() {
    return "Address{" +
            "city='" + city +
            ", country='" + country +
            ", zipCode='" + zipCode +
            ", street='" + street +
            '}';
}
}
