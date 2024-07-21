package pl.infoshare.clinicweb.file;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

public interface FileRepository {

    <T> List<T> readFromFile(String filePath, TypeReference<List<T>> typeReference) throws IOException;

    void writeToFile(Object object, String filePath);

}
