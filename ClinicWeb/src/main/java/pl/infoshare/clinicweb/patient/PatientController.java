package pl.infoshare.clinicweb.patient;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
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
    public String patientFormSubmission(@Valid PersonDetails patientDetails, BindingResult detailsBinding,
                                        @Valid Address patientAddress, BindingResult addressBinding,
                                        Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("doctors", doctorService.findAll());

        if (detailsBinding.hasErrors() || addressBinding.hasErrors()) {

            return "patient";

        } else {

            redirectAttributes.addFlashAttribute("success", "Utworzono nowego pacjenta w bazie.");
            patientService.savePatient(new Patient(patientDetails, patientAddress));

            return "redirect:/patient";
        }

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
            patientService.remove(byPesel);
        } else {
            model.addAttribute("error", "Nie znaleziono pacjenta o podanym numerze pesel: " + pesel);
        }
        return "search-patient";
    }

    @GetMapping("/search-patient")
    public String searchPatientByPesel( @RequestParam("pesel") Model model,String pesel) {
        Patient byPesel = patientService.findByPesel(pesel);
        model.addAttribute("patient", new Patient());

        return "search-patient";
    }

    @PostMapping("/delete-patient")
    public String deletePatient(@RequestParam("pesel") String pesel, Model model) {
        Patient byPesel = patientService.findByPesel(pesel);
        if (byPesel != null) {
            patientService.remove(byPesel);
        }
        return "redirect:/patients";
    }

    @GetMapping("/delete-patient")
    public String showDeletePatientForm(@RequestParam("pesel") String pesel, Model model) {
        Patient byPesel = patientService.findByPesel(pesel);
        model.addAttribute("patient", byPesel);
        return "delete-patient";
    }



}
