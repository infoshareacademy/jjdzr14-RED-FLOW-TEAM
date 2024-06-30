package pl.infoshare.clinicweb.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listDoctorsSurgery")
public class ViewListDoctorsSurgery {
    @GetMapping
    public String getDoctors() {
        return "listDoctorsSurgery";
    }

}

