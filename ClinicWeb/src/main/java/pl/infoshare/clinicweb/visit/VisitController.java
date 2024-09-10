package pl.infoshare.clinicweb.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;

import java.util.Optional;


@Controller
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;


    public VisitController(VisitService visitService, DoctorService doctorService, PatientService patientService) {

        this.visitService = visitService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/result")
    public String displayResult(Model model) {

        return "result";
    }

    @GetMapping("/visit")
    public String saveVisit(@ModelAttribute("patient") Patient patient,
                            @ModelAttribute("visit") Visit visit, @ModelAttribute("doctor") Doctor doctor, Model model) {

        model.addAttribute("doctors", doctorService.findAllDoctors());
        model.addAttribute("patients", patientService.findAllPatients());

        return "visit";
    }

    @PostMapping("/visit")
    public String visitFormSubmission(@Valid Visit visit, BindingResult visitBindingResult,
                                      @RequestParam(value = "patientId", required = false) Long patientId,
                                      @RequestParam(value = "doctorId", required = false) Long doctorId,
                                      Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("doctors", doctorService.findAllDoctors());
        model.addAttribute("patients", patientService.findAllPatients());


        if (visitBindingResult.hasErrors()) {

            return "visit";

        } else {

            redirectAttributes.addFlashAttribute("success", "Pomyślnie zarejestrowano. " +
                    "Dziękujemy za rejestrację!");


            visitService.saveVisit(visit, doctorId, patientId);

            return "redirect:/visit";
        }

    }

    @GetMapping("/visits")
    public String allVisits(Model model) {
        model.addAttribute("allVisits", visitService.getAllVisits());
        return "visits";
    }

    @PostMapping("/cancel")
    public String cancelVisit(@RequestParam("id") Long id, Model model) {

        Optional<VisitDto> visit = visitService.findVisitById(id);
        model.addAttribute("allVisits", visit);

        visitService.cancelVisit(visit.get());

        return "redirect:/visits";
    }


    @GetMapping("/cancel")
    public String showCancelVisitForm(@ModelAttribute Patient patient, Model model) {
        model.addAttribute("allVisits", visitService.getAllVisits());
        return "redirect:/visits";
    }
}

