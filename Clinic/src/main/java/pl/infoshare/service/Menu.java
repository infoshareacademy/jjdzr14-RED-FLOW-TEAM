package pl.infoshare.service;

import pl.infoshare.model.Patient;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        ArrayList<Patient> listPatient = null;
        String path = null;
        do {

            DisplayMenu.chooseMenu();
            option = scanner.nextInt();

            switch (option) {

                case 1:
                    FileService.findAllDoctor();
                    break;
                case 2:
                    FileService.findAllPatient();
                    break;
                case 3:
                    FileService.writeToFile(listPatient, path);
                    System.out.println("case3");
                    break;
                case 0:
                    System.out.println("Bye bye");
                    break;
                default:
                    System.out.println("Zly wybór");
                    break;
            }
        } while (option != 0);


    }
}


