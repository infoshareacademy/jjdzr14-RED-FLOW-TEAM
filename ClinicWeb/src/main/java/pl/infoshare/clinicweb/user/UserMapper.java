package pl.infoshare.clinicweb.user;

import lombok.Data;

@Data
public class UserMapper {

    private String email;
    private String password;
    private Role role;

    public UserMapper toDto(AppUser user){

        UserMapper userDto = new UserMapper();

        userDto.setEmail(user.getEmail());
        userDto.setPassword(userDto.getPassword());
        userDto.setRole(user.getRole());


        return userDto;
    }

    public AppUser toEntity(UserMapper userDto){

        AppUser user = new AppUser();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }
}
