package pl.infoshare.clinicweb.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;


@Controller
@Slf4j
public class PatientController {

    private final PatientService patientService;
    private final FileService fileService;

    public PatientController(PatientService patientService, FileService fileService) {

        this.patientService = patientService;
        this.fileService = fileService;
    }

    @GetMapping("/patient")
    public String patientForm(Model model) {

        model.addAttribute("personDetails", new PersonDetails());
        model.addAttribute("address", new Address());

        return "patientForm";
    }

    @PostMapping("/register")
    public String patientFormSubmission(@ModelAttribute PersonDetails patientDetails, @ModelAttribute Address patientAddress, Model model) {

        model.addAttribute("personDetails", new PersonDetails());
        model.addAttribute("address", new Address());


        patientService.savePatient(new Patient(patientDetails, patientAddress));

        return "result";

    }

    @GetMapping("/patients-list")
    @ResponseBody
    public List<PatientDto> viewPatients() {

        return patientService.findAll();
    }


}
