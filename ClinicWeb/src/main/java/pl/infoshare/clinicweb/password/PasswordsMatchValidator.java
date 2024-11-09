package pl.infoshare.clinicweb.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.infoshare.clinicweb.user.AppUserDto;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, AppUserDto> {
    @Override
    public boolean isValid(AppUserDto appUserDto, ConstraintValidatorContext constraintValidatorContext) {

        if(appUserDto == null){
            return true;
        }

        return appUserDto.getPassword().equals(appUserDto.getConfirmPassword());
    }
}
