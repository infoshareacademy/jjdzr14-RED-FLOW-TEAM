package pl.infoshare;

import pl.infoshare.model.Doctor;
import pl.infoshare.service.FileService;
import pl.infoshare.service.Registration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class App {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> listDoctor = List.of();
        String path = "/home/marek/IdeaProjects/jjdzr14-RED-FLOW-TEAM/Clinic/src/main/resources/doctor.json";
        FileService.readFile(String.valueOf(listDoctor), path);

        // FileService.findAllDoctor();
        //Registration.registerUser();


    }
}