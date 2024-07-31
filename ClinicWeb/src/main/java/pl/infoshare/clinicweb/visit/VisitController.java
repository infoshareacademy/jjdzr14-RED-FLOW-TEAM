package pl.infoshare.clinicweb.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDateTime;

@Controller
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {

        this.visitService = visitService;
    }

    @GetMapping("/addVisit")
    public String patientVisitForm(Model model) {

        model.addAttribute("patient", new Patient());

        return "createVisit";
    }

    @GetMapping("/result")
    public String displayResult(Model model) {

        return "result";
    }

    @GetMapping("/saveVisit")
    public String saveVisit( Model model) {

        model.addAttribute("visit", new Visit());
        model.addAttribute("patient", new Patient());
        model.addAttribute("person", new PersonDetails());


        return "saveVisit";
    }

    @PostMapping("/saveVisit")
    public String visitFormSubmission(@ModelAttribute(value = "visit") @Valid Visit visit, BindingResult bindingResulVisit,
                                      @RequestParam(value = "visitDate", required = false) LocalDateTime visitDate,
                                      @RequestParam(value = "patient") @Valid Patient patient,
                                      BindingResult bindingResultPatient, Model model
    ) {


        if (bindingResulVisit.hasErrors() || bindingResultPatient.hasErrors()) {
            return "saveVisit";
        }

        visitService.saveVisit(visit);

        return "redirect:/result";
    }

}
