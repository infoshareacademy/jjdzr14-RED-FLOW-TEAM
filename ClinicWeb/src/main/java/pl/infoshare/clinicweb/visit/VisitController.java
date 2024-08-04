package pl.infoshare.clinicweb.visit;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.clinicweb.doctor.DoctorDto;
import pl.infoshare.clinicweb.doctor.Specialization;
import pl.infoshare.clinicweb.patient.PatientDto;

import java.time.LocalDateTime;

@Controller
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {

        this.visitService = visitService;
    }

    @GetMapping("/addVisit")
    public String visitForm(@ModelAttribute("visit") Visit visit, @ModelAttribute("patient") PatientDto patient, @ModelAttribute("doctor") DoctorDto doctor) {


        return "addVisit";
    }

    @GetMapping("/result")
    public String displayResult(Model model) {

        return "result";
    }

    @PostMapping("/saveVisit")
    public String visitFormSubmission(@ModelAttribute(value = "visit") @Valid Visit visit, BindingResult bindingResulVisit,
                                      @RequestParam(value = "visitDate", required = false) LocalDateTime visitDate,
                                      @ModelAttribute(value = "patient") @Valid PatientDto patient, BindingResult bindingResultPatient,
                                      DoctorDto doctor,
                                      @RequestParam("specialization") Specialization specialization
    ) {


        if (bindingResulVisit.hasErrors() || bindingResultPatient.hasErrors()) {
            return "addVisit";
        }


        //do poprawy, musi wyladowac w service

        visit.setPatient(patient);
        patient.setDoctor(doctor);
        doctor.setSpecialization(specialization.getDescription());
        doctor.setName("testowe");
        doctor.setSurname("testoweNazwisko");


        visitService.saveVisit(visit);

        return "redirect:/result";
    }

    @RequestMapping("/visits")
    public String allVisits() {
        return "visits";
    }
}
