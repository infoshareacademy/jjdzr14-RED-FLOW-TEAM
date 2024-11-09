package pl.infoshare.clinicweb.user;

import lombok.Data;

@Data
public class UserMapper {

    public AppUserDto toDto(AppUser user) {


        AppUserDto userDto = new AppUserDto();

        userDto.setEmail(user.getEmail());
        userDto.setPassword(userDto.getPassword());
        userDto.setRole(user.getRole());


        return userDto;
    }

    public AppUser toEntity(AppUserDto userDto){

        AppUser user = new AppUser();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }
}
