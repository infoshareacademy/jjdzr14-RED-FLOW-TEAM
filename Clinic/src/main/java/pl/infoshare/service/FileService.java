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


public static void vievDoctors() {

    String path = "Clinic/src/main/resources/listDoctors.txt";
    File file = new File(path);
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




    public static void writeToFile(Doctor fullName, String filePath) {
        try {
            List<Doctor>listDoctor = new LinkedList<>();

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.valueOf(fullName));

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }


//        String pathOfResources = "Clinic/src/main/resources/plikTekstowy.txt";
//    String value = "Kowalski Jan";
//    Path path = Path.of(pathOfResources);
//
//    try {
//
//        Files.writeString(path, value, StandardOpenOption.CREATE);
//
//    } catch (
//            IOException e) {
//
//        System.out.println("wystapił błąd podczas zapisywania pliku");
//    }
//    try {
//        String content = Files.readString(path);
//        System.out.println(content);
//    } catch (
//            IOException e) {
//        System.err.println("bład odczytu pliku ");
//    }

//public static void writeToFile()  {
//
//    String path = "src/main/resources/plikTekstowy.txt";
//    String value = "Jan Kowalski";
//    File file = new File(path);
//    Scanner in = null;
//    String zdanie = "";
//    try {
//        in = new Scanner(file);
//        zdanie = in.nextLine();
//
//        System.out.println(zdanie);
//
//    } catch (FileNotFoundException e) {
//        throw new RuntimeException(e);
//    }


//    try {
//        Files.writeString(path,value, StandardOpenOption.CREATE);
//    } catch (IOException e) {
//        String error = e.getMessage();
//        System.out.println(error);
//    }
//
//    try {
//        String content = Files.readString(path);
//        System.out.println(content);
//    } catch (IOException e) {
//        String error = e.getMessage();
//        System.err.println(error);
//    }
    }

}




