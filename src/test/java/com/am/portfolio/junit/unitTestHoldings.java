package com.am.portfolio.junit;

import com.am.portfolio.dataRepo;
import com.am.portfolio.holdings;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import static org.mockito.Mockito.mock;

public class unitTestHoldings {

    @Test
    public void testInitData() {
        holdings h = new holdings();

        dataRepo dataSource = mock(dataRepo.class);

        h.initData();

    }

    @Test
    public void testCovertMsg2Json() {

        JSONParser parser = new JSONParser();

        try {
            File file  = new File("src/test/resource", "input.data.01.json");
            JSONArray records = (JSONArray) parser.parse(new FileReader(file.getAbsoluteFile()));

            for (Object record : records) {
                JSONObject object = (JSONObject) record;

//                JSONArray keys = object.names ();
//                for (int i = 0; i < keys.length (); ++i) {
//
//                    String key = keys.getString (i); // Here's your key
//                    String value = object.getString (key); // Here's your value
//
                }

                System.out.println(record.toString());
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
