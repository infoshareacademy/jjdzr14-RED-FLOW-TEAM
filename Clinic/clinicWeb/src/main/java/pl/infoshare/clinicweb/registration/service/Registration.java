package pl.infoshare.clinicweb.registration.service;

import pl.infoshare.clinicweb.FileService;
import pl.infoshare.clinicweb.adress.model.Address;
import pl.infoshare.clinicweb.patient.model.Patient;
import pl.infoshare.clinicweb.user.model.PersonDetails;
import pl.infoshare.clinicweb.user.model.User;

import static pl.infoshare.clinicweb.FileService.PATIENT_PATH;

public class Registration {
    public static void addPatient(User user) {

        Patient patient = new Patient();
        Address address = new Address();
        PersonDetails details = new PersonDetails();

        patient.setPersonDetails(details);
        patient.setAddress(address);
        patient.setUser(user);


        System.out.println("DODAWANIE PACJENTA");

        try {

            PersonDetails.addName(patient);
            PersonDetails.addSurname(patient);
            PersonDetails.addPesel(patient);
            patient.getPersonDetails().setIdNumber();

            FileService.writeToFile(patient, PATIENT_PATH);

        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }
    }
}
