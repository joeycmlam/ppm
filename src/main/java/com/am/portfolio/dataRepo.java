package com.am.portfolio;

import com.am.util.fileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dataRepo {


    private String filePath;
    private String fileName;

    public dataRepo(String Path, String Name) {
        this.filePath = Path;
        this.fileName = Name;
    }

    public ArrayList<holding> getPositions() {
        ArrayList<holding> aryHoldings;
        holding aHolding;


        File file  = new File(this.filePath, this.fileName);
        aryHoldings = new ArrayList<>();
        try {
            List<HashMap<String, String>> records = fileUtil.readCSVFile(file.getAbsolutePath());
            for (HashMap<String, String> record : records) {
                aHolding = new holding ();
                aHolding.acctid = record.get("acctId");
                aHolding.stockid = record.get("stockcode");
                aHolding.unit = Double.parseDouble(record.get("unit"));
                aHolding.mv = Double.parseDouble(record.get("mv"));
                aHolding.country=record.get("country");
                aHolding.region = record.get("region");
                aHolding.sector = record.get("sector");
                aryHoldings.add(aHolding);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return aryHoldings;
    }


}
