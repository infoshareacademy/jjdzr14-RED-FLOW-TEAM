package pl.infoshare.service;

import pl.infoshare.exeption.DataImportException;
import pl.infoshare.model.Details;
import pl.infoshare.model.Doctor;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    static List<Doctor> doctorTest;
    String pathDoctors = "Clinic/src/main/resources/listDoctors.txt";

    public static void addDoctor() {
        Scanner scanner = new Scanner(System.in);

        Doctor doctor = new Doctor();
        Details details = new Details();
        System.out.println("Podaj Imię");
        details.setName(scanner.nextLine());
        System.out.println("Podaj Nazwisko");
        details.setSurname(scanner.nextLine());
        System.out.println("Podaj mumer telefonu");
        details.setPhoneNumber(scanner.nextLine());
        System.out.println("podaj ID number");
        details.setIdNumber(scanner.nextInt());
        scanner.nextLine();
        doctor.setDetails(details);
        System.out.println("Pojdaj specjalizację");
        doctor.setSpecialization(scanner.nextLine());
        System.out.println("online?");
        doctor.setOnline(scanner.nextBoolean());
        System.out.println("podaj dostępność");
        doctor.setAvailability(scanner.nextBoolean());


        List<Doctor> doctorTest = new ArrayList<>();
        doctorTest.add(doctor);
        for (Doctor doctor1 : doctorTest) {
            System.out.println(doctor1);
            writeToFile(doctor1, "/home/grzegorzacademy/Pulpit/ud282-master" +
                    "/jjdzr14-RED-FLOW-TEAM/Clinic/src/main/resources/plikTekstowy.txt");
        }
    }
    private  static void fileReader(String pathDoctors) {

        File file = new File(pathDoctors);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie ma takiego pliku");
        }

        int lines = 0;
        while (scan.hasNextLine()) {
            String name = scan.nextLine();
            System.out.println(name);
            lines++;
        }
        scan.close();

    }
    public static void vievDoctors(){
        fileReader( "Clinic/src/main/resources/plikTekstowy.txt");
    }
    public static void vievPatient (){
        fileReader("Clinic/src/main/resources/listPatient.txt");
    }


    public static void writeToFile(Doctor fullName, String filePath) {
        try {
            List<Doctor> listDoctor = new LinkedList<>();

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.valueOf(fullName));

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }
}




