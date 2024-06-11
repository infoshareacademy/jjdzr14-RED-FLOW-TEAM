package pl.infoshare.clinicweb.doctor;

import pl.infoshare.clinicweb.adress.Address;
import pl.infoshare.clinicweb.user.PersonDetails;
import pl.infoshare.clinicweb.user.PersonDetailsService;
import pl.infoshare.clinicweb.user.User;
import pl.infoshare.file.FileService;

import static pl.infoshare.file.FileService.DOCTOR_PATH;

public class DoctorService implements DoctorRepository {

    public void addDoctor(User user) {

        PersonDetails details = new PersonDetails();
        Address address = new Address();
        Doctor doctor = new Doctor();
        doctor.setPersonDetails(details);
        doctor.setAddress(address);
        doctor.setUser(user);

        try {

            System.out.println("DODAWANIE LEKARZA ");

            PersonDetailsService.addName(doctor);
            PersonDetailsService.addSurname(doctor);
            PersonDetailsService.addPesel(doctor);
            doctor.getPersonDetails().setIdNumber();

            FileService.writeToFile(doctor, DOCTOR_PATH);


        } catch (NullPointerException npe) {

            System.out.println("Proba odwolania sie do nieistniejacego obiektu.");
        }


    }

    @Override
    public void addDoctor(Doctor user) {

    }

    @Override
    public void findAllDoctors() {

    }

    @Override
    public void deleteDoctor(Doctor doctor) {

    }

    @Override
    public void updateDoctor(Doctor doctor) {

    }

    @Override
    public void findDoctorByKey(String name, String surname) {

    }
}
