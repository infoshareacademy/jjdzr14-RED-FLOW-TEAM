package pl.infoshare.clinicweb.patient;

import lombok.Data;

@Data
public class PatientDto {

    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String pesel;


}
