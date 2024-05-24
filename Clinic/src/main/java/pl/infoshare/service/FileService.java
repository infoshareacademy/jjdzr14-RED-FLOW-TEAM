package pl.infoshare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.infoshare.exeption.DataImportException;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import static java.nio.file.Paths.get;

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

    public static void viewUsers() {
        fileReader("Clinic/src/main/resources/usernames.txt");
    }


    public static void writeToFile(Object object, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            objectToJson(object, filePath);


            bw.newLine();

            bw.close();

        } catch (IOException  e) {

            System.out.println(e.getMessage());

        }

    }


    public static void objectToJson(Object newObject, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            Set<Object> objectSet;
            File file = new File(filePath);

            if (file.exists() && !file.isDirectory() && file.length() != 0) {
                String jsonContent = new String(Files.readAllBytes(get(filePath)));
                objectSet = mapper.readValue(jsonContent, new TypeReference<LinkedHashSet<Object>>() {
                });
            } else {
                objectSet = new LinkedHashSet<>();
            }
            objectSet.add(newObject);
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            String jsonData = writer.writeValueAsString(objectSet);

            Files.write(get(filePath), jsonData.getBytes());

            System.out.println("Updated collection written to file:");

        } catch (JsonProcessingException e) {
            System.err.println("Json processing error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("File IO error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}








