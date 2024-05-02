package pl.infoshare;
import pl.infoshare.model.Patient;
import pl.infoshare.service.PatientService;


public class App {
    public static void main(String[] args) {

        Patient patient = new Patient();
        PatientService.addPatient(patient);


    }


}
