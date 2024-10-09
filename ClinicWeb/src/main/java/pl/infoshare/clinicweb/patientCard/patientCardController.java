package pl.infoshare.clinicweb.patientCard;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.clinicweb.patient.PatientDto;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.visit.VisitDto;
import pl.infoshare.clinicweb.visit.VisitService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        if (patientDtoOpt.isPresent()) {
            PatientDto patientDto = patientDtoOpt.get();
            PatientCardDTO patientCard = new PatientCardDTO();

            patientCard.setPatientFirstName(patientDto.getName());
            patientCard.setPatientLastName(patientDto.getSurname());
            patientCard.setPatientPesel(patientDto.getPesel());

            Optional<VisitDto> visitById = visitService.findVisitById(id);

            patientCard.setDateOfVisit(visitById.get().getVisitDate());
            System.out.println(patientCard.getDateOfVisit());

            model.addAttribute("patient", patientDto);
            model.addAttribute("patientCard", patientCard);
            visitService.findVisitById(id).ifPresent(visitDto -> model.addAttribute("visitData", visitDto));

            return "patient-card";
        } else {
            model.addAttribute("error", "Patient not found");
            return "error-page";
        }
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
