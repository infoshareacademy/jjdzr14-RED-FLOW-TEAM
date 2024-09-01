package pl.infoshare.clinicweb.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

}
