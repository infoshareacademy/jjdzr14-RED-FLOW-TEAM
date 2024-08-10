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
    public String patientFormSubmission(@ModelAttribute PersonDetails patientDetails, @ModelAttribute Address patientAddress, Model model, @ModelAttribute DoctorService doctorService) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("personDetails", new PersonDetails());
        model.addAttribute("address", new Address());

        patientService.savePatient(new Patient(patientDetails, patientAddress));

        return "result";

    }

    @GetMapping("/patients")
    public String viewPatients(Model model) {

        model.addAttribute("listPatient", patientService.findAll());


        return "patientsList";
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

    @GetMapping("/fullDetailsPatient")
    public String fullDetailPatient(@RequestParam(value = "pesel", required = false) String pesel, Model model) {
        model.addAttribute("fullDetailPatient", patientService.findByPesel(pesel));
        return "/fullDetailsPatient";
    }


}
