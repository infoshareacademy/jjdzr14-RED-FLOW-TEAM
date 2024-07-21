package pl.infoshare.clinicweb.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class LoginController {

    @ResponseBody
    public String ViewStartPage(){
        return "index";
    }
}
