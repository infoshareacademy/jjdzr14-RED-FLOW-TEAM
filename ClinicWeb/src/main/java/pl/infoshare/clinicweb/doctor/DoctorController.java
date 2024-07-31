package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {

        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public String viewDoctors(Model model, @RequestParam(required = false, value="specialization") Specialization specialization) {

        List<DoctorDto> doctors;

        doctors = specialization == null ? doctorService.findAll() : doctorService.findBySpecialization(specialization);

        model.addAttribute("listDoctor", doctors);

        return "doctorsList";
    }


}
