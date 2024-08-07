package pl.infoshare.clinicweb.doctor;

public interface DoctorRepository {

    void addDoctor(Doctor user);

    void findAllDoctors();

    void deleteDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void findDoctorByKey(String name, String surname);
}
