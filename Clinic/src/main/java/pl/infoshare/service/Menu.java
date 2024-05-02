package pl.infoshare.service;
import pl.infoshare.model.Doctor;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        List <Doctor> doctor;


        do {

            DisplayMenu.chooseMenu();
            option = scanner.nextInt();

            switch (option) {

                case 1:
                    Doctor.printDoctors();
                    break;
                case 2:
                    System.out.println("case2");
                    break;
                case 3:
                    System.out.println("case3");
                    break;
                case 0:
                    System.out.println("Bye bye");
                    break;
                default:
                    System.out.println("Zly wybor");
                    break;
            }
        } while (option != 0);


    }
}


