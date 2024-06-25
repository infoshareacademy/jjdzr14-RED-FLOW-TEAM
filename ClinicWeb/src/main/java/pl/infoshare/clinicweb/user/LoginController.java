package pl.infoshare.clinicweb.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String ViewStartPage(){
        return "index";
    }
}
