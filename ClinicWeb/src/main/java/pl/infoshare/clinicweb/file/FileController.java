package pl.infoshare.clinicweb.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.infoshare.clinicweb.doctor.DoctorService;
import pl.infoshare.clinicweb.patient.PatientService;

import static pl.infoshare.clinicweb.file.GeneratorData.generateAndSaveData;

@RequiredArgsConstructor
@Controller
public class FileController {


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
        GeneratorData.generateAndSaveData(model);
        return "index";
    }

    @PostMapping("/clear-data")
    public String clearData() {
        doctorService.deleteFileDoctor();
        patientService.deleteFilePatient();
        return "index";
    }
}

