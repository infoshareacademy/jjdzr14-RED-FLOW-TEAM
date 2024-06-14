package pl.infoshare.clinicweb.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService implements FileRepository {

    public static final String DOCTOR_PATH = "ClinicWeb/src/main/resources/doctors.json";
    public static final String PATIENT_PATH = "ClinicWeb/src/main/resources/patients.json";

    @Override
    public <T> void fileReader(String filePath) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<T> deserializedObjects = mapper.readValue(Files.readAllBytes(Path.of(filePath)),new TypeReference<List<T>>(){});
            deserializedObjects.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readPatients(String filePatient) {

    }

    @Override
    public void readDoctors(String fileDoctors) {

    }

    @Override
    public void saveObjectToJsonFile(Object object, String filePath) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JSONArray jsonArray = this.parseArrayFromFile(filePath);


        try (FileWriter fileWriter = new FileWriter(filePath, false)) {

            jsonArray.add(object);

            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);

            fileWriter.write(jsonPretty);


        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public JSONArray parseArrayFromFile(String filePath) {

        JSONParser parser = new JSONParser();

        try {
            return (JSONArray) parser.parse(new FileReader(filePath));
            
        } catch (ParseException e) {

            return new JSONArray();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku. ");
            return null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
