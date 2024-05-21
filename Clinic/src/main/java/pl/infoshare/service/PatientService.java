package pl.infoshare.service;

import pl.infoshare.model.Address;
import pl.infoshare.model.Patient;
import pl.infoshare.model.PersonDetails;
import pl.infoshare.model.User;


public class PatientService extends Login {

    protected static final String PATIENT_PATHJSON = "Clinic/src/main/resources/patient.json";

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

            FileService.writeToFile(patient, PATIENT_PATHJSON);

        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }
    }
}