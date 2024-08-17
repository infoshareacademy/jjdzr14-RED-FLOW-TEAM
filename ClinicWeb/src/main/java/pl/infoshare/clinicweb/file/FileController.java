package pl.infoshare.clinicweb.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.infoshare.clinicweb.doctor.Doctor;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.Patient;
import pl.infoshare.clinicweb.patient.PatientService;

@RequiredArgsConstructor
@Controller
public class FileController {
    int count = 15;
    GeneratorData generatorData = new GeneratorData();

    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public FileController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping("/create-Data")
    public String createData() {
        generateAndSaveData();
        return "redirect:/patients";
    }

    @GetMapping("/create-Data")
    public String create(Model model) {
        generateAndSaveData(model);
        return "index";
    }

    @DeleteMapping("/clear-Data")
    public String clearData() {
        doctorService.deleteFileDoctor();
        patientService.deleteFilePaitent();

        return "redirect:/patients";
    }

    @GetMapping("/clear-Data")
    public String clearData(Model model) {
        doctorService.deleteFileDoctor();
        patientService.deleteFilePaitent();
        return "index";
    }

    private void generateAndSaveData() {
        generatorData.writeRandomObjects(count, new Patient());
        generatorData.writeRandomObjects(count, new Doctor());
    }

    private void generateAndSaveData(Model model) {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        generatorData.writeRandomObjects(count, patient);
        generatorData.writeRandomObjects(count, doctor);
        model.addAttribute("patient", patient);
        model.addAttribute("doctor", doctor);
    }
}

