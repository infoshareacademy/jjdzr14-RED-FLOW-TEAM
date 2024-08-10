package pl.infoshare.clinicweb.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.user.PersonDetails;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PatientController {

    private final PatientService patientService;

    private final DoctorService doctorService;

    @GetMapping("/patient")
    public String patientForm(Model model) {

        model.addAttribute("personDetails", new PersonDetails());
        model.addAttribute("address", new Address());
        model.addAttribute("doctors", doctorService.findAll());

        return "patient";
    }

    @PostMapping("/patient")
    public String patientFormSubmission(@ModelAttribute PersonDetails patientDetails, @ModelAttribute Address patientAddress, Model model) {
        model.addAttribute("personDetails", new PersonDetails());
        model.addAttribute("address", new Address());

        patientService.savePatient(new Patient(patientDetails, patientAddress));
        model.addAttribute("listPatient", patientService.findAll());
        return "patientsList";

    }

    @GetMapping("/patients")
    public String viewPatients(Model model) {

        model.addAttribute("listPatient", patientService.findAll());

        return "patientsList";
    }

    @PostMapping("/addVisit")
    public String showSearchForm(@RequestParam(value = "pesel", required = false) String pesel, Model model) {
        if (pesel != null && !pesel.isEmpty()) {
            Patient byPesel = patientService.findByPesel(pesel);
            model.addAttribute("searchForPesel", byPesel);
        } else if (patientService.findByPesel(pesel) != null) {
            model.addAttribute("searchForPesel", patientService.findByPesel(pesel));
        }
        return "addVisit";
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "search";
    }

    @PostMapping("/search")
    public String searchPatient(@RequestParam("pesel") String pesel, Model model, Address address) {
        Patient patient = patientService.findByPesel(pesel);
        if (patient != null) {
            model.addAttribute("patient", patient);
            model.addAttribute("address", address);
        } else {
            model.addAttribute("error", "Patient not found");
        }
        return "search";
    }

    @PostMapping("/edit")
    public String editPatient(@ModelAttribute("patient") Patient patient, Model model, Address address) {
        patientService.saveOrUpdatePatient(patient, address);
        model.addAttribute("patient", patient);
        model.addAttribute("address", address);
        model.addAttribute("success", "Patient data updated successfully");
        return "redirect:patients";
    }

    @GetMapping("/update-patient")
    public String fullDetailPatient(@RequestParam(value = "pesel", required = false) @ModelAttribute String pesel, Model model) {
        model.addAttribute("updatePatient", patientService.findByPesel(pesel));
        return "update-patient";
    }

    @PostMapping("/search-patient")
    public String searchPatientByPesel(@RequestParam("pesel") String pesel, Model model, Address address) {
        Patient byPesel = patientService.findByPesel(pesel);
        if (patientService.findByPesel(pesel) != null) {
            model.addAttribute("searchForPesel", byPesel);
        } else {
            model.addAttribute("error", "Nie znaleziono pacjenta o podanym numerze pesel: " + pesel);
        }
        return "search-patient";
    }

    @GetMapping("/search-patient")
    public String searchPatientByPesel(Model model) {
        model.addAttribute("patient", new Patient());
        return "search-patient";
    }

}
