package pl.infoshare.clinicweb.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.infoshare.clinicweb.patient.PatientService;

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
    @Pattern(regexp = "^\\d{9}$", message = "Pole musi składać się z 9 cyfr.")
    private String phoneNumber;
    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "[0-9]{11}", message = "Pole musi zawierać 11 cyfr. ")
    private String pesel;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate setDateOfBirth(String pesel) {

        LocalDate localDate = PatientService.decodeDateOfBirth(pesel);

        return localDate;


    }

}
