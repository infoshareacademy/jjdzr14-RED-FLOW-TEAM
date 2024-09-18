package pl.infoshare.clinicweb.patientCard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class patientCardController {

    private static PatientCardService patientCardService;


    @GetMapping("/patientCard")
    public String viewPatientCard(Model model) {

        return "patient-card";
    }


}
