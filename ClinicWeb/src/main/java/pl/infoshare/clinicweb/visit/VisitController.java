package pl.infoshare.clinicweb.visit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.patient.PatientDto;

@Controller
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {

        this.visitService = visitService;
    }

    @RequestMapping("/addVisit")
    public String visitForm(Model model) {

        model.addAttribute("patient", new PatientDto());
        model.addAttribute("doctor", new DoctorDto());

        return "addVisit";
    }

    @GetMapping("/result")
    public String displayResult(Model model) {

        return "result";
    }

    @PostMapping("/saveVisit")
    public String visitFormSubmission(@RequestParam("specialization") Specialization specialization, @ModelAttribute PatientDto patient, Model model, @ModelAttribute DoctorDto doctor ) {

        patient.setDoctor(doctor);
        patient.getDoctor().setName("testName");
        patient.getDoctor().setSurname("testSurname");
        patient.getDoctor().setSpecialization(specialization.getDescription());

        visitService.saveVisit(new Visit(patient));

        return "redirect:/result";
    }

}
