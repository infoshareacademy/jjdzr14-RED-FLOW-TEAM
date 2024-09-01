package pl.infoshare.clinicweb.doctor;

import lombok.Data;

@Data
public class DoctorDto {

    private long id;
    private String name;
    private String surname;
    private String specialization;


}
