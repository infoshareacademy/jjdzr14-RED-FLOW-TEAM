package pl.infoshare.clinicweb.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private AppUserService userService;

    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("user", new AppUser());

        return "user/registry";
    }

    @PostMapping("/register")
    public String registerFormSubmission(@Valid @ModelAttribute AppUser user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/registry";
        }

        userService.saveUser(user);

        return "redirect:/registry";
    }

    @GetMapping("/login")
    public String login() {

        AppUser user = getPrincipal();

        if (user != null) {
            return "redirect:/index";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {


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
