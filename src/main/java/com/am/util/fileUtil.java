package com.am.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class fileUtil {

    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> getCSVFile(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String headerLine;
            headerLine = br.readLine();
            String header[] = headerLine.split(COMMA_DELIMITER);
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    public static List<HashMap<String, String>> readCSVFile(String fileNme) throws Exception {

        FileReader aFileReader  = new FileReader(fileNme);
        return readCSVFile(aFileReader);
    }

    public static List<HashMap<String, String>> readCSVFile(FileReader file) throws Exception  {
        List<HashMap<String, String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(file)) {
            String headerLine;
            headerLine = br.readLine();
            String header[] = headerLine.split(COMMA_DELIMITER);
            String line;
            HashMap<String, String> aRecord;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                aRecord = new HashMap<>();
                for (int i = 0; i < values.length; i++) {
                    aRecord.put(header[i], values[i]);
                }
                records.add(aRecord);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return records;
    }
}
