package pl.infoshare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.infoshare.exeption.DataExportException;
import pl.infoshare.exeption.DataImportException;
import pl.infoshare.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileService {


    public static void dataToJson() {
        String path = "Clinic/src/main/resources/listPatient.json";
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Patient> listPatient = new ArrayList<>();
        Doctor d1 = new Doctor(new Details("Adam", "Nowak", "505-69-963", 2), "Internista", true, true);
        Address a1 = new Address("Gdańsk", "Polska", "85-669", "Władysława Jagieły");
        Clinic c1 = new Clinic(a1, "Meodentica");
        Patient p1 = new Patient(new Details("Roman", "Adamowicz"), 18, "5882225586", c1, a1, d1);
        listPatient.add(p1);
        try {
            String jsonData = mapper.writeValueAsString(listPatient);
            mapper.writeValue(new File(path), listPatient);
            System.out.println(jsonData);
        } catch (JsonProcessingException e) {
            throw new DataExportException("bład zapisu pliku");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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




