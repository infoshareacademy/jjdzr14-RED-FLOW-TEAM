package pl.infoshare.clinicweb.doctor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DoctorDto {

    private String name;
    private String surname;
    private String specialization;
    @Id
    @GeneratedValue
    private long id;



}
