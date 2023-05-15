package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVHandlerImpl implements CSVHandler {

  private static final String COMMA_DELIMITER = "," ;

  /**
   * Read entries from CSV
   * @param fileName
   * @return CSV entries
   */
  @Override
  public List<List<String>> readFromCSV(String fileName) {
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        records.add(Arrays.asList(values));
      }
    }catch(Exception e){
     // handle the exception or handle in advice
    }
    return records;
  }
}
