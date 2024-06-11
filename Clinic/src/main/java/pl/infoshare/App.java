package pl.infoshare;

import org.json.simple.parser.ParseException;

import pl.infoshare.service.DoctorService;
import pl.infoshare.service.Login;
import pl.infoshare.service.Registration;


import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException, ParseException {



        DoctorService.filterBySpecialization("kardiolog");
        System.out.println(DoctorService.filterBySpecialization("chirurg"));
        System.out.println(DoctorService.filterBySpecialization("neurolog"));


    }


}