package pl.infoshare.clinicweb.patientCard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.PatientService;
import pl.infoshare.clinicweb.visit.VisitDto;
import pl.infoshare.clinicweb.visit.VisitService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class patientCardController {


    private final PatientCardService patientCardService;
    private final VisitService visitService;
    private final PatientCardMapper patientCardMapper;

    @GetMapping("/patient-card")
    public String createPatientCard(@RequestParam(value = "id", required = false)
             Long id, Model model) {
        if (id == null) {
            model.addAttribute("error", "ID not found");
            return "error-page";
        }

        VisitDto visit = visitService.findVisitById(id).get();
        PatientCardDTO patientCardDTO = new PatientCardDTO();
        patientCardDTO.setPatientFirstName(visit.getPatientName());
        patientCardDTO.setPatientLastName(visit.getPatientSurname());
        patientCardDTO.setDoctorFirstName(visit.getDoctorName());
        patientCardDTO.setDoctorLastName(visit.getDoctorSurname());
        patientCardDTO.setPatientId(visit.getPatientId());
        patientCardDTO.setPatientPesel(visit.getPatientPesel());
        patientCardDTO.setDoctorId(visit.getDoctorId());
        patientCardDTO.setDateOfVisit(visit.getVisitDate());

        model.addAttribute("visit", visit);
        model.addAttribute("patientCard", patientCardDTO);

        return "patient-card";
    }

    @GetMapping("/patient-appointment")
    public String createPatientAppointment(@RequestParam(value = "id", required = false) Long id,
                                           Model model) {
        if (id == null) {
            model.addAttribute("error", "Invalid patient ID");
            return "error-page";
        }
        PatientCardDTO patientAppointments = patientCardService.findById(id);

        model.addAttribute("patientCard", patientAppointments);
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

        patientCardService.patientCardSave(patientCard, patientCardDTO.getPatientId(), patientCardDTO.getDoctorId());

        model.addAttribute("successMessage", "Karta pacjenta została pomyślnie zapisana!");

        return "redirect:/index";
    }



}

