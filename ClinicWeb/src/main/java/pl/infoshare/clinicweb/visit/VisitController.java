package pl.infoshare.clinicweb.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientDto;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.time.LocalDateTime;

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
    public String saveVisit( Model model, @ModelAttribute("visit") Visit visit, @ModelAttribute("patient") Patient patient, @ModelAttribute PersonDetails personDetails) {


        model.addAttribute("doctors", doctorService.findAll());



        return "saveVisitForm";
    }

    @PostMapping("/saveVisit")
    public String visitFormSubmission(@ModelAttribute(value = "visit") @Valid Visit visit, BindingResult bindingResulVisit,
                                      @RequestParam(value = "visitDate", required = false) LocalDateTime visitDate,
                                      @ModelAttribute(value="patient") @Valid Patient patient, BindingResult bindingResultPatient,
                                      @ModelAttribute(value = "personDetails") @Valid PersonDetails personDetails, BindingResult bindingResultDetails,
                                      Model model) {



        if (bindingResulVisit.hasErrors()) {
            return "saveVisitForm";
        }

        visitService.saveVisit(new Visit(patient));

        return "redirect:/result";
    }

}
