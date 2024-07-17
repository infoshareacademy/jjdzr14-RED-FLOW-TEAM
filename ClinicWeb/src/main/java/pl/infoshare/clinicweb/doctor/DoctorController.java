package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public String viewDoctors(Model model) {

        model.addAttribute("listDoctor", doctorService.findAll());

        return "allDoctorsList";
    }

    @GetMapping("/specializations")
    public String viewSpecializations(Model model, @RequestParam("specialization") Specialization specialization) {

        model.addAttribute("filteredDoctors", doctorService.findBySpecialization(specialization));

        return "filteredDoctorsList";
    }
}
