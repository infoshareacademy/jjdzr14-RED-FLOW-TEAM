package pl.infoshare.clinicweb.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class addVisit {

@RequestMapping("/addVisit")
    public String addVisits() {
    return "addVisit";
}

}
