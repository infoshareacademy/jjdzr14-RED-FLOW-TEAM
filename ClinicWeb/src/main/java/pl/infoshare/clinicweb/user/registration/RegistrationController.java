package pl.infoshare.clinicweb.user.registration;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.infoshare.clinicweb.user.service.AppUserService;

@Controller
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final AppUserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("user", new AppUserDto());
        log.info("New user registration form was requested.");

        return "user/registry";
    }

    @PostMapping("/register")
    public String registerFormSubmission(@Valid @ModelAttribute("user") AppUserDto user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("user", user);
            log.info("Validation error occured when registering user.");
            return "user/registry";
        }

        userService.saveUser(user);
        model.addAttribute("success", "Pomyślnie zarejestrowano użytkownika");
        log.info("User with ID: {} was successfully created and saved to DB.", user.getId());

        return "redirect:/registry";
    }

    @GetMapping("/register/admin")
    public String registerAdminForm(Model model) {

        model.addAttribute("user", new AppUserDto());
        log.info("New user registration form was requested.");

        return "user/registry";
    }

    @PostMapping("register/admin")
    public String registerAdminFromSubmission(@Valid @ModelAttribute("admin") AppUserDto admin, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("user", admin);
            log.info("Validation error occured when registering user.");
            return "user/registry";
        }

        userService.saveUser(admin);
        model.addAttribute("success", "Pomyślnie zarejestrowano użytkownika");
        log.info("User with ID: {} was successfully created and saved to DB.", admin.getId());

        return "user/registry";
    }
}
