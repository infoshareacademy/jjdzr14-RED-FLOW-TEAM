package pl.infoshare.clinicweb.file;

import org.json.simple.JSONArray;

public interface FileRepository {

    <T> void fileReader(String filePath);

    void readPatients(String filePatient);

    void readDoctors(String fileDoctors);

    void saveObjectToJsonFile(Object object, String filePath);

    JSONArray parseArrayFromFile(String file);
}
