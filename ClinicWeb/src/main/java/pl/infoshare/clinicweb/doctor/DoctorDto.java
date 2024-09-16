package pl.infoshare.clinicweb.doctor;

import lombok.Data;

@Data
public class DoctorDto {

    private long id;
    private String pesel;
    private String name;
    private String surname;
    private Specialization specialization;
    private String country;
    private String street;
    private String city;
    private String zipCode;
    private String houseNumber;
    private String flatNumber;


}
