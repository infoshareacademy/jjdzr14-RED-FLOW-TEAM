package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors-list")
    public String viewDoctors(Model model) {

        model.addAttribute("listDoctor", doctorService.findAll());

        return "allDoctorsList";
    }

    @GetMapping("/specializations-list")
    public String viewSpecializations(Model model) {

        model.addAttribute("filteredDoctors", doctorService.findBySpecialization("Neurolog"));

        return "filteredDoctorsList";
    }
}
