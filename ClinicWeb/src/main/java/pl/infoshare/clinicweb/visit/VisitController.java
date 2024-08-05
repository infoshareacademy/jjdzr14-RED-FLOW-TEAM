package pl.infoshare.clinicweb.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;

@SessionAttributes({"patient"})

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
    public String saveVisit(@ModelAttribute("patient") Patient patient, @ModelAttribute("visit") Visit visit, @ModelAttribute("doctor") DoctorDto doctor, Model model) {

        model.addAttribute("doctors", doctorService.getAll());

        return "saveVisitForm";
    }

    @PostMapping("/saveVisit")
    public String visitFormSubmission(@Valid Visit visit, BindingResult visitBindingResult,
                                      @Valid Patient patient, BindingResult patientBindingResult,
                                      @RequestParam(value = "doctorPesel", required = false) String pesel,
                                      @Valid DoctorDto doctor, Model model) {

        model.addAttribute("doctors", doctorService.getAll());

        if (visitBindingResult.hasErrors() || patientBindingResult.hasErrors()) {
            return "saveVisitForm";
        }

        doctor = doctorService.findByPesel(pesel);

        visitService.setVisitAttributes(patient, doctor, visit);
        visitService.saveVisit(visit);

        return "redirect:/result";
    }

}
