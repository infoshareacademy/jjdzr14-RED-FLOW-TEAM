package pl.infoshare.clinicweb.doctor;

import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public List<DoctorDto> viewDoctors() {

        return doctorService.findAll();
    }

    @GetMapping("/specializations-list")
    @ResponseBody
    public List<DoctorDto> viewSpecializations() {

        return doctorService.findBySpecialization("Neurolog");
    }
}
