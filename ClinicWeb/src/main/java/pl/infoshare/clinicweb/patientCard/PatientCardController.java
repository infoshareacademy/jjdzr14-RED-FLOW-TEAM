package pl.infoshare.clinicweb.patientCard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.visit.VisitDto;
import pl.infoshare.clinicweb.visit.VisitService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PatientCardController {


    private final PatientCardService patientCardService;
    private final VisitService visitService;
    private final PatientCardMapper patientCardMapper;

    @GetMapping("/patient-card")
    public String createPatientCard(@RequestParam(value = "id", required = false)
                                    Long id, Model model) {

        VisitDto visit = visitService.findVisitById(id);
        PatientCardDTO patientCardDTO = PatientCardService.getPatientCardDTO(visit);

        model.addAttribute("visit", visit);
        model.addAttribute("patientCard", patientCardDTO);

        return "patient-card";
    }



    @GetMapping("/patient-appointments")
    public String getPatientAppointments(@RequestParam(value = "id", required = false) Long id, Model model) {


        List<PatientCard> patientAppointments = patientCardService.findAllPatientCardByPatientId(id);

        model.addAttribute("patientAppointments", patientAppointments);
        return "patient-appointments";
    }

    @PostMapping("/patient-card")
    public String savePatientCard(
            @Valid @ModelAttribute("patientCard") PatientCardDTO patientCardDTO,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Wystąpiły błędy walidacyjne.");
            return "error-page";
        }
        PatientCard patientCard = patientCardMapper.toEntity(patientCardDTO);

        if (patientCardDTO.getPatientId() == null || patientCardDTO.getDoctorId() == null) {
            model.addAttribute("error", "Identyfikator pacjenta lub lekarza nie może być pusty.");
            return "error-page";
        }


        patientCardService.patientCardSave(patientCard);

        model.addAttribute("successMessage", "Karta pacjenta została pomyślnie zapisana!");

        return "patient-appointments";
    }


}

