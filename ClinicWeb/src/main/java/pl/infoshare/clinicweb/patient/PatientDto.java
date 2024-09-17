package pl.infoshare.clinicweb.patient;

import lombok.Data;

@Data
public class PatientDto {

    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String pesel;
    private String country;
    private String street;
    private String city;
    private String zipCode;
    private String houseNumber;
    private String flatNumber;


}
