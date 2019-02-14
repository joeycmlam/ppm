package com.am.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fileUtil {

    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> getFile(String fileName) {
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
}
