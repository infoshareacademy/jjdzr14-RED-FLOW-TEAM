package pl.infoshare.clinicweb.patient;

import lombok.Data;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.user.PersonDetails;

@Data
public class PatientDto {

    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String pesel;
    private DoctorDto doctor;


}
