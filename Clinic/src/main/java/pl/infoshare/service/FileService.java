package pl.infoshare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.infoshare.exeption.DataImportException;

import java.io.*;
import java.util.Scanner;

public class FileService {


    private static void fileReader(String pathDoctors) {

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

    public static void vievDoctors() {
        fileReader("Clinic/src/main/resources/doctorList.txt");
    }

    public static void vievPatient() {
        fileReader("Clinic/src/main/resources/listPatient.txt");
    }

    public static void writeToFile(Object object, String filePath) {
        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String jsonData = dataToJson(object);
            bw.write(jsonData);

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public static String dataToJson(Object object) {



        ObjectMapper mapper = new ObjectMapper(); System.err.println(e.getMessage());
       
        try {
            String jsonData = mapper.writeValueAsString(object);
            System.out.println(jsonData);
            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);



        }

    }


}






