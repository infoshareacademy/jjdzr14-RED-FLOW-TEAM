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

        List<Doctor> doctors;

        doctors = specialization == null ? doctorService.getAll() : doctorService.findBySpecialization(specialization);

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

    @GetMapping("/search-doctor")
    public String searchDoctorByPesel(@ModelAttribute Doctor doctor) {

        return "search-doctor";
    }

    @PostMapping("/search-doctor")
    public String searchDoctorByPesel(@RequestParam(value = "pesel", required = false) String pesel, Model model) {

        Doctor byPesel = doctorService.findByPesel(pesel);

        if (doctorService.findByPesel(pesel) != null) {
            model.addAttribute("searchForPesel", byPesel);
        } else {
            model.addAttribute("error", "Nie znaleziono lekarza o podanym numerze pesel: " + pesel);
        }
        return "search-doctor";
    }

    @GetMapping("/update-doctor")
    public String fullDetailDoctor(@RequestParam(value = "pesel", required = false) String pesel, Model model) {

        model.addAttribute("updateDoctor", doctorService.findByPesel(pesel));

        return "update-doctor";
    }

    @PostMapping("/update-doctor")
    public String editDoctor(@ModelAttribute("doctor") Doctor doctor, Model model, Address address, RedirectAttributes redirectAttributes) {

        doctorService.saveOrUpdateDoctor(doctor, address);
        model.addAttribute("doctor", doctor);
        model.addAttribute("address", address);
        redirectAttributes.addFlashAttribute("success", "Zaktualizowano dane lekarza.");

        return "redirect:doctors";
    }

    @GetMapping("/delete-doctor")
    public String showDeleteDoctorForm(@RequestParam("pesel") String pesel, Model model) {

        Doctor byPesel = doctorService.findByPesel(pesel);
        model.addAttribute("doctor", byPesel);

        return "delete-doctor";
    }

    @PostMapping("/delete-doctor")
    public String deleteDoctor(@RequestParam("pesel") String pesel, RedirectAttributes redirectAttributes) {

        Doctor byPesel = doctorService.findByPesel(pesel);

        if (byPesel != null) {
            doctorService.remove(byPesel);
            redirectAttributes.addFlashAttribute("success", "Usunięto dane lekarza.");
        }
        return "redirect:/doctors";
    }


}
