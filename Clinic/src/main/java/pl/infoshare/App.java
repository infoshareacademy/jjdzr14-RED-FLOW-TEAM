package pl.infoshare;
import pl.infoshare.model.Patient;
import pl.infoshare.model.Role;
import pl.infoshare.model.User;
import pl.infoshare.service.PatientService;


public class App {
    public static void main(String[] args) {


        User user = new User();
        user.setRole(Role.ADMIN);
        System.out.println(user.getRole().getRoleDescription());
        System.out.println(user.toString());
    }
}