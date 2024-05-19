package pl.infoshare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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


    private static void fileReader(String path) {

        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Nie ma takiego pliku");
        }

        while (scan.hasNextLine()) {
            String name = scan.nextLine();
            System.out.println(name);
        }
        scan.close();

    }

    public static void findAllDoctor() {
        fileReader("Clinic/src/main/resources/doctor.json");
    }

    public static void findAllPatient() {
        fileReader("Clinic/src/main/resources/patient.json");
    }

    public static void findAllUser() {
        fileReader("Clinic/src/main/resources/user.json");
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
            return prettyJsonString;

        } catch (JsonProcessingException e) {

            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Object jsonToData(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            JsonNode jsonData = mapper.readTree(new File("/home/marek/IdeaProjects/jjdzr14-RED-FLOW-TEAM/Clinic/src/main/resources/doctor.json"));
            String name = jsonData.get("name").asText();
            String surnname = jsonData.get("surname").asText();
            // JsonElement jsonElement = JsonParser.parseString(jsonData);
            // String prettyJsonString = gson.fromJson(jsonData, String.class);
            System.out.println(jsonData);
            return name;

        } catch (JsonProcessingException e) {

            System.err.println(e.getMessage());
            throw new RuntimeException(e);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void readFile(String json, String filePath) {

        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            JSONParser jsonParser = new JSONParser();

            String jsonData = jsonToData(json).toString();
            System.out.println(jsonData);

            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }
    }
}





