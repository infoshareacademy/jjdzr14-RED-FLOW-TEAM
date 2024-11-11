package pl.infoshare.clinicweb.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AppUserDto toDto(AppUser user) {


        AppUserDto userDto = new AppUserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(userDto.getPassword());
        userDto.setRole(user.getRole());


        return userDto;
    }

    public AppUser toEntity(AppUserDto userDto) {

        AppUser user = new AppUser();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }
}
