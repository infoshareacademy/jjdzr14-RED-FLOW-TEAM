package pl.infoshare.service;

import pl.infoshare.model.Details;
import pl.infoshare.model.Doctor;
import pl.infoshare.model.User;


public class DoctorService {

    private static final String DOCTOR_PATH = "Clinic/src/main/resources/doctorList.txt";

    public static void addDoctor(User user) {


        Details details = new Details();
        Doctor doctor = new Doctor();
        doctor.setDetails(details);
        doctor.setUser(user);


        try {

            System.out.println("DODAWANIE LEKARZA ");

            Utils.addName(doctor);
            Utils.addSurname(doctor);

            FileService.writeToFile(doctor, DOCTOR_PATH);


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }


    }


}

