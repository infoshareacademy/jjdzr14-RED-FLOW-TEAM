package pl.infoshare.clinicweb.user;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "/^\\S+@\\S+\\.\\S+$/")
    @NotEmpty(message = "Pole nie może byc puste")
    @Column(nullable = false, unique = true)
    private String email;
    @Size(min = 6, message = "Minimalna długość hasła to 6 znaków.")
    @NotEmpty(message = "Pole nie może być puste.")
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Pole nie może być puste.")
    private Role role;

}
