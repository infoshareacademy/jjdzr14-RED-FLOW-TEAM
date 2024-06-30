package pl.infoshare.clinicweb.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listDoctorsFamily")
public class ViewListDoctorsFamily {
    @GetMapping
    public String getDoctors() {
        return "listDoctorsFamily";
    }

}

