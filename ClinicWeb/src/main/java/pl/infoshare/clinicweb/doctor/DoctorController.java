package pl.infoshare.clinicweb.doctor;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.infoshare.clinicweb.patient.Address;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.PeselUtils;

import java.util.List;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {

        this.doctorService = doctorService;
    }


    @RequestMapping("/doctors")
    public String viewDoctors(Model model, @RequestParam(required = false, value = "specialization") Specialization specialization) {

        List<DoctorDto> doctors;

        doctors = specialization == null ? doctorService.findAll() : doctorService.findBySpecialization(specialization);

        model.addAttribute("listDoctor", doctors);

        return "doctorsList";
    }

    @GetMapping("/doctor")
    public String doctorForm(Model model) {

        model.addAttribute("personDetails", new PersonDetails());
        model.addAttribute("address", new Address());

        return "doctor";
    }

    @PostMapping("/doctor")
    public String doctorFormSubmission(@ModelAttribute Doctor doctor,
                                       @Valid PersonDetails doctorDetails, BindingResult detailsBinding,
                                       @Valid Address doctorAddress, BindingResult addressBinding,
                                       @RequestParam("specialization") Specialization specialization,
                                       @RequestParam("pesel") String pesel,
                                       RedirectAttributes redirectAttributes, Model model) {


        if (detailsBinding.hasErrors() || addressBinding.hasErrors() || !PeselUtils.hasPeselCorrectDigits(pesel)) {

            model.addAttribute("peselError", "Wprowadzony numer PESEL jest niepoprawny.");
            return "doctor";

        } else {

            redirectAttributes.addFlashAttribute("success", "Utworzono nowego lekarza w bazie.");

            doctorService.setDoctorAttributes(doctor, doctorDetails, doctorAddress, specialization);
            doctorService.saveDoctor(doctor);

            return "redirect:/doctor";
        }

    }


}
