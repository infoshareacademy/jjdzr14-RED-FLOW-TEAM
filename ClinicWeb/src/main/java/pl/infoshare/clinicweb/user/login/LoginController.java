package pl.infoshare.clinicweb.user.login;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.infoshare.clinicweb.user.entity.AppUser;

import java.util.Collection;

@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {

        AppUser user = getPrincipal();

        if (user != null) {


            log.info("User with email: {} was successfully logged in.", user.getEmail());
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
