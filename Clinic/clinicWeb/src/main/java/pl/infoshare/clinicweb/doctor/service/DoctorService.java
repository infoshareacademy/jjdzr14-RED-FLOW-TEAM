package pl.infoshare.clinicweb.doctor.service;

import pl.infoshare.clinicweb.FileService;
import pl.infoshare.clinicweb.adress.model.Address;
import pl.infoshare.clinicweb.doctor.model.Doctor;
import pl.infoshare.clinicweb.user.model.PersonDetails;
import pl.infoshare.clinicweb.user.model.User;

import static pl.infoshare.clinicweb.FileService.DOCTOR_PATH;


public class DoctorService {

    public static void addDoctor(User user) {

        PersonDetails details = new PersonDetails();
        Address address = new Address();
        Doctor doctor = new Doctor();
        doctor.setPersonDetails(details);
        doctor.setAddress(address);
        doctor.setUser(user);

        try {

            System.out.println("DODAWANIE LEKARZA ");

            PersonDetails.addName(doctor);
            PersonDetails.addSurname(doctor);
            PersonDetails.addPesel(doctor);
            doctor.getPersonDetails().setIdNumber();

            FileService.writeToFile(doctor, DOCTOR_PATH);


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }


    }
}
