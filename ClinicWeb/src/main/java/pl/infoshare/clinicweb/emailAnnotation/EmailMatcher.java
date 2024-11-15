package pl.infoshare.clinicweb.emailAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pl.infoshare.clinicweb.user.registration.AppUserDto;
import pl.infoshare.clinicweb.user.service.AppUserService;

@Component
public final class EmailMatcher implements ConstraintValidator<EmailMatcherValidator, AppUserDto> {

        private final AppUserService userService;

        public EmailMatcher(AppUserService userService){
            this.userService = userService;
        }

        @Override
        public void initialize(EmailMatcherValidator constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(AppUserDto appUserDto, ConstraintValidatorContext constraintValidatorContext) {


            if (appUserDto == null) {
                return true;
            }

            boolean isEmailValid = !userService.isUserAlreadyRegistered(appUserDto.getEmail());

            if (!isEmailValid) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Podany adres email istnieje już w bazie.")
                        .addPropertyNode("email")
                        .addConstraintViolation();
            }

            return isEmailValid ;
        }
    }
