package pl.infoshare.service;


import pl.infoshare.model.Address;
import pl.infoshare.model.Doctor;
import pl.infoshare.model.PersonDetails;
import pl.infoshare.model.User;


public class DoctorService {

    private static final String DOCTOR_PATH = "Clinic/src/main/resources/doctor.json";

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

