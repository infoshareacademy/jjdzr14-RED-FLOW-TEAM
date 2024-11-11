package pl.infoshare.clinicweb.user;

import lombok.Data;
import pl.infoshare.clinicweb.annotation.PasswordMatcherValidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@PasswordMatcherValidator
public class AppUserDto {

    private Long id;
    @NotEmpty(message = "Pole email nie może być puste.")
    @Pattern(regexp = ("/^\\S+@\\S+\\.\\S+$/"))
    private String email;
    @NotEmpty(message = "Pole hasło nie może być puste.")
    @Size(min = 6, message = "Hasło musi składać się z przynajmniej 6 znaków.")
    private String password;
    @NotEmpty(message = "Pole nie może być puste.")
    @Size(min = 6, message = "Hasło musi składać się z przynajmniej 6 znaków.")
    private String confirmPassword;
    @NotEmpty(message = "Podaj swoją rolę użytkownika:")
    private Role role;
}
