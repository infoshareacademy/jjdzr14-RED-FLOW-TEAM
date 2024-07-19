package pl.infoshare.clinicweb.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.clinicweb.file.FileService;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.List;


@Controller
@Slf4j
public class PatientController {

    private final PatientService patientService;
    private FileService fileService;

    @Autowired
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
    @PostMapping("/addVisit")
    public String showSearchForm(@RequestParam(value = "pesel", required = false) String pesel, Model model) {
        if (pesel != null && !pesel.isEmpty()) {
            Patient byPesel = patientService.findByPesel(pesel);
            model.addAttribute("searchForPesel", byPesel);
        }
        else if (patientService.findByPesel(pesel) != null) {
            model.addAttribute("searchForPesel", patientService.findByPesel(pesel));
        }
        return "addVisit";
    }

}
