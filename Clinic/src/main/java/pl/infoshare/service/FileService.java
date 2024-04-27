package pl.infoshare.service;

import pl.infoshare.exeption.DataImportException;
import pl.infoshare.model.Doctor;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    static List<Doctor> doctorTest;
    String pathDoctors = "Clinic/src/main/resources/listDoctors.txt";

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
        fileReader( "Clinic/src/main/resources/listDoctors.txt");
    }
    public static void vievPatient (){
        fileReader("Clinic/src/main/resources/listDoctors.txt");
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




