package pl.infoshare.service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {


    public static void writeToFile(String fullName, String filePath) {


        try {

            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(fullName);
            bw.newLine();

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }


//    String pathOfResources = "Clinic/src/main/resources/plikTekstowy.txt";
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
    }

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






