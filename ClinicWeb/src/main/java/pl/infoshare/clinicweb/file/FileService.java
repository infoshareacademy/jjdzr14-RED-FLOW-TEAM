package pl.infoshare.clinicweb.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileService implements FileRepository {

    private final ObjectMapper mapper;

    public FileService(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public <T> List<T> readFromFile(String filePath, TypeReference<List<T>> typeReference) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(formatter);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, deserializer);

        mapper.registerModule(module);

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            mapper.registerModule(new JavaTimeModule());

            byte[] fileBytes = Files.readAllBytes(Path.of(filePath));

            return mapper.readValue(fileBytes, typeReference);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile(Object object, String filePath) throws RuntimeException {

        mapper.registerModule(new JavaTimeModule());

        JSONArray jsonArray = this.parseArrayFromFile(filePath);


        try (FileWriter fileWriter = new FileWriter(filePath, false)) {

            jsonArray.add(object);

            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);

            fileWriter.write(jsonPretty);


        } catch (NoSuchFileException | FileNotFoundException e) {
            log.error(e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private JSONArray parseArrayFromFile(String filePath) {

        JSONParser parser = new JSONParser();

        try {
            return (JSONArray) parser.parse(new FileReader(filePath));

        } catch (ParseException e) {

            return new JSONArray();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku. ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JSONArray();

    }
}
