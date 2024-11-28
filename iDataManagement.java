package oop;

import java.util.List;

public interface iDataManagement<T> {
    List<T> readDataFromFile(String filePath);

    void writeDataToFile(String filePath, List<T> data);
}