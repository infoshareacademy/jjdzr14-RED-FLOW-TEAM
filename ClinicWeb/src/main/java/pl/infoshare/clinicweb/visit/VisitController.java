package pl.infoshare.clinicweb.visit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;

@SessionAttributes("patient")
@Controller
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;

    public VisitController(VisitService visitService, DoctorService doctorService) {

        this.visitService = visitService;
        this.doctorService = doctorService;
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
    public String saveVisit(@ModelAttribute("visit") Visit visit, @ModelAttribute("patient") Patient patient, Model model) {

        model.addAttribute("doctors", doctorService.findAll());

        return "saveVisitForm";
    }

    @PostMapping("/saveVisit")
    public String visitFormSubmission(Visit visit, Patient patient, Model model) {

        if (patient == null) {
            return "redirect:/addVisit";
        }
        visitService.saveVisit(visit);


        return "redirect:/result";
    }

}
