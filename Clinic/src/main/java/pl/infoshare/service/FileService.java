package pl.infoshare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
        fileReader("Clinic/src/main/resources/doctor.json");
    }

    public static void vievPatient() {
        fileReader("Clinic/src/main/resources/patient.json");
    }




    public static void writeToFile(Object object, String filePath) {

        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            JSONParser jsonParser = new JSONParser();

            String jsonData = dataToJson(object);
            bw.write(jsonParser.parse(jsonData).toString());

            bw.newLine();

            bw.close();

        } catch (IOException | ParseException e) {

            System.out.println(e.getMessage());

        }

    }

    public static String dataToJson(Object object) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            String jsonData = mapper.writeValueAsString(object);
            JsonElement jsonElement = JsonParser.parseString(jsonData);
            String prettyJsonString = gson.toJson(jsonElement);
            System.out.println(jsonData);
            return mapper.writeValueAsString(prettyJsonString);

        } catch (JsonProcessingException e) {

            System.err.println(e.getMessage());
            throw new RuntimeException(e);


        }

    }


}






