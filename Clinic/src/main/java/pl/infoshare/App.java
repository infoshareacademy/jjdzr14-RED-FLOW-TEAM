package pl.infoshare;

import pl.infoshare.service.FileService;
import pl.infoshare.service.Registration;

import java.io.FileNotFoundException;


public class App {
    public static void main(String[] args) throws FileNotFoundException {

        Registration.registerUser();


    }
}