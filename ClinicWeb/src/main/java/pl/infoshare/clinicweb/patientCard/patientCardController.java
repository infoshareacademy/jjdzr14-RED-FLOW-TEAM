package pl.infoshare.clinicweb.patientCard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.visit.VisitService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class patientCardController {

    private final PatientService patientService;
    private final PatientCardService patientCardService;
    private final VisitService visitService;


        @GetMapping("/patient-card")
        public String createOrFetchPatientCard(@RequestParam("id") Long patientId, Model model) {

            PatientCard patientCard = patientCardService.createOrGetPatientCard(patientId);
            LocalDateTime dateOfVisit = patientCard.getDateOfVisit();
            model.addAttribute("currentDateTime", dateOfVisit);
            model.addAttribute("patientCard", patientCard);



            return "patient-card";
        }
}
