package pl.infoshare.clinicweb.passwordAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.infoshare.clinicweb.user.registration.AppUserDto;

public final class PasswordMatcher implements ConstraintValidator<PasswordMatcherValidator, AppUserDto> {

    @Override
    public void initialize(PasswordMatcherValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AppUserDto appUserDto, ConstraintValidatorContext constraintValidatorContext) {

        if (appUserDto == null) {
            return true;
        }

        boolean isPasswordValid = appUserDto.getPassword().equals(appUserDto.getConfirmPassword());

        if (!isPasswordValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Hasła muszą być identyczne")
                    .addPropertyNode("password")
                    .addConstraintViolation();
        }

        return isPasswordValid ;
    }
}
