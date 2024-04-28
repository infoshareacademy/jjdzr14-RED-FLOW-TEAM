package pl.infoshare.service;
import pl.infoshare.model.Patient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {


    public static void writeToFile(Object object, String filePath) {

        if (object instanceof Patient) {

            Patient patient = (Patient) object;
        }

        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(String.valueOf(object));
            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }
    }

}






