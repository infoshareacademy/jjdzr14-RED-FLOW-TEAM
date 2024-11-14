package pl.infoshare.clinicweb.emailAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.infoshare.clinicweb.user.registration.AppUserDto;

public final class EmailMatcher implements ConstraintValidator<EmailMatcherValidator, AppUserDto> {

        @Override
        public void initialize(EmailMatcherValidator constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(AppUserDto appUserDto, ConstraintValidatorContext constraintValidatorContext) {

            if (appUserDto == null) {
                return true;
            }

            boolean isEmailValid = !appUserDto.getEmail().equals(appUserDto.getEmail());

            if (!isEmailValid) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Podany adres email istnieje już w bazie.")
                        .addPropertyNode("email")
                        .addConstraintViolation();
            }

            return isEmailValid ;
        }
    }
