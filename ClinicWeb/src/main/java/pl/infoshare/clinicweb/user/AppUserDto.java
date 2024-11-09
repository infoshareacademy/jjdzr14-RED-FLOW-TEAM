package pl.infoshare.clinicweb.user;

import lombok.Data;

@Data
public class AppUserDto {

    private String email;
    private String password;
    private Role role;
}
