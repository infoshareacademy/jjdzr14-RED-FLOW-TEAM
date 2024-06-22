package pl.infoshare.clinicweb.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.infoshare.clinicweb.user.PersonDetails;


@Controller
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public String patientForm(Model model) {
        model.addAttribute("patientForm", new PersonDetails());
        return "patientForm";
    }

    @PostMapping("/patient")
    public String patientFormSubmission(@ModelAttribute Patient patient, Model model) {
        model.addAttribute("patientForm", patient);

        return "result";

    }


}
