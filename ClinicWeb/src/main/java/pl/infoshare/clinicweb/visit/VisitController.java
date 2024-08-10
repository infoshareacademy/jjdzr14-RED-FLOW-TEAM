package pl.infoshare.clinicweb.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;


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

    @GetMapping("/addVisit")
    public String saveVisit(@ModelAttribute("patient") Patient patient, @ModelAttribute("visit") Visit visit, @ModelAttribute("doctor") DoctorDto doctor, Model model) {

        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("patients", patientService.getAll());

        return "addVisit";
    }

    @PostMapping("/addVisit")
    public String visitFormSubmission(@Valid Visit visit, BindingResult visitBindingResult,
                                      @RequestParam(value = "patientPesel", required = false) String patientPesel,
                                      @Valid Patient patient,
                                      @RequestParam(value = "doctorPesel", required = false) String doctorPesel,
                                      @Valid DoctorDto doctor, Model model) {

        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("patients", patientService.getAll());

        if (visitBindingResult.hasErrors()) {
            return "addVisit";
        }

        doctor = doctorService.findByPesel(doctorPesel);
        patient = patientService.findByPesel(patientPesel);

        visitService.setVisitAttributes(patient, doctor, visit);
        visitService.saveVisit(visit);

        return "redirect:/result";
    }

}
