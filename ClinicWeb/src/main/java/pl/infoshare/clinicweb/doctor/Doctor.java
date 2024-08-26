package pl.infoshare.clinicweb.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor {

    private String specialization;
    private boolean online;
    private boolean availability;
    @Id
    @GeneratedValue
    private long id;

}


