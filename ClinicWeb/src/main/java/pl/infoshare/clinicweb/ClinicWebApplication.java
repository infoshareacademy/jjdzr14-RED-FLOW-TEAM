package pl.infoshare.clinicweb;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.infoshare.clinicweb.doctor.DoctorService;

@SpringBootApplication

public class ClinicWebApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ClinicWebApplication.class, args);


        DoctorService doctorService = context.getBean(DoctorService.class);


        System.out.println(doctorService.findById(1));

    }
}