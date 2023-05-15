package service;

import java.util.List;

public interface CSVHandler {

  List<List<String>> readFromCSV(String fileName);

}
