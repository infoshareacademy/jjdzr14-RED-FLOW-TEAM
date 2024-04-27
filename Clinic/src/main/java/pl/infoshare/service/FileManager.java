package pl.infoshare.service;

import pl.infoshare.model.Clinic;

public interface FileManager {
    Clinic importData();
    void exportData(Clinic clinic);
}
