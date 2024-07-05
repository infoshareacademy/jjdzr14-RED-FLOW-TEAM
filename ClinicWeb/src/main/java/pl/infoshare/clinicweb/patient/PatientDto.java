package pl.infoshare.clinicweb.patient;

import lombok.*;
import pl.infoshare.clinicweb.user.PersonDetails;


@Data

public class PatientDto {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;


}
