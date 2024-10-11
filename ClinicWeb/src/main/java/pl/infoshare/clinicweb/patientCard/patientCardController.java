package pl.infoshare.clinicweb.patientCard;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.patient.PatientDto;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.visit.VisitDto;
import pl.infoshare.clinicweb.visit.VisitService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class patientCardController {

    private final PatientService patientService;
    private final PatientCardService patientCardService;
    private final VisitService visitService;


    @GetMapping("/patient-card")
    public String createPatientCard(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("error", "Invalid patient ID");
            return "error-page";
        }

        Optional<PatientDto> patientDtoOpt = patientService.findById(id);
        if (!patientDtoOpt.isPresent()) {
            model.addAttribute("error", "Patient not found");
            return "error-page";
        }

        PatientDto patientDto = patientDtoOpt.get();
        long patientIdFromDto = patientDto.getId();

        Optional<VisitDto> visitOpt = visitService.findVisitById(patientIdFromDto);

        if (!visitOpt.isPresent()) {
            model.addAttribute("error", "Visit not found for patient");
            return "error-page";
        }

        PatientCardDTO patientCard = createPatientCardDTO(patientDto);
        model.addAttribute("patientCard", patientCard);

        return "patient-card";
    }

    private PatientCardDTO createPatientCardDTO(PatientDto patientDto) {
        PatientCardDTO patientCard = new PatientCardDTO();
        patientCard.setPatientFirstName(patientDto.getName());
        patientCard.setPatientLastName(patientDto.getSurname());
        patientCard.setPatientPesel(patientDto.getPesel());
        return patientCard;
    }

    @PostMapping("/patient-card")
    public String savePatientCard(@ModelAttribute("patientCard") @Valid PatientCardDTO patientCard, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Validation errors occurred");
            return "patient-card";
        }

        try {
            patientCardService.patientCardSave(patientCard);
            model.addAttribute("successMessage", "Patient card saved successfully!");
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to save patient card");
            return "patient-card";
        }
    }
}
