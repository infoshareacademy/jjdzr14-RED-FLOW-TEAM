package pl.infoshare.clinicweb.user.mapper;

import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.user.entity.AppUser;
import pl.infoshare.clinicweb.user.registration.AppUserDto;

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
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }
}
