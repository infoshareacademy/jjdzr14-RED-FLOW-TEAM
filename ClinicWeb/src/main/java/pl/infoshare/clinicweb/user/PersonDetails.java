package pl.infoshare.clinicweb.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class PersonDetails {

    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 2, max = 20, message = "Pole musi zawierać od 2 do 20 znaków.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Pole musi składać się z samych liter")
    private String name;
    @NotEmpty(message = "Pole nie może być puste")
    @Size(min = 2, max = 20, message = "Pole musi zawierać od 2 do 20 znaków.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Pole musi składać się z samych liter")
    private String surname;
    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;
    private LocalDate birthDate;
    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "[0-9]{11}", message = "Pole musi zawierać 11 cyfr. ")
    private String pesel;
    @Enumerated(EnumType.STRING)
    private Gender gender;


}
