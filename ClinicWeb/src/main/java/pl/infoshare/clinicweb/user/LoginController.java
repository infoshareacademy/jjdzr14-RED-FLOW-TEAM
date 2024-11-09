package pl.infoshare.clinicweb.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {

    private AppUserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("user", new AppUser());
        log.info("New user registration form was requested.");

        return "user/registry";
    }

    @PostMapping("/register")
    public String registerFormSubmission(@Valid @ModelAttribute AppUser user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("Validation error occured when registering user.");
            return "user/registry";
        }

        userService.saveUser(user);
        log.info("User with ID: {} was successfully created and saved to DB.", user.getId());

        return "redirect:/registry";
    }

    @GetMapping("/login")
    public String login() {

        AppUser user = getPrincipal();

        if (user != null) {
            log.info("User with id: {} was successfully logged in.", user.getId());
            return "redirect:/index";

        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {

        log.info("User was successfully logged out.");
        return "index";

    }

    private AppUser getPrincipal() {
        AppUser user = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AppUser) {
            user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }


}
