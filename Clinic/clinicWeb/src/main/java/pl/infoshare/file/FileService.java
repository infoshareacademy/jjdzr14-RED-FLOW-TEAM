package pl.infoshare.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.infoshare.clinicweb.menu.Login;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import static java.nio.file.Paths.get;

public class FileService {
    public static final String DOCTOR_PATH = "Clinic/src/main/resources/doctor.json";
    public static final String PATIENT_PATH = "Clinic/src/main/resources/patient.json";


    private static void fileReader(String pathDoctors) {

        File file = new File(pathDoctors);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Nie ma takiego pliku");
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            objectToJson(object, filePath);


            bw.newLine();

            bw.close();

        } catch (IOException e) {

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

    public static void getDataFromJsonUser(String filename) {

        String login = "";
        String password = "";
        JSONArray jsonArray = (JSONArray) convertFileToJSON(filename);

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject singlePerson = (JSONObject) jsonArray.get(i);

            JSONObject userData = (JSONObject) singlePerson.get("user");

            login = userData.get("login").toString();
            password = userData.get("password").toString();

            Login.userData.put(login, password);
        }


    }

    public static JSONArray convertFileToJSON(String fileName) {

        JSONArray jsonArray = null;

        try {

            JSONParser parser = new JSONParser();

            jsonArray = (JSONArray) parser.parse(new FileReader(fileName));


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return jsonArray;
    }

}
