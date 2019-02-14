package com.am.portfolio;

import com.am.util.fileUtil;

import java.io.File;
import java.util.ArrayList;
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
        List<List<String>> records = fileUtil.getFile(file.getAbsolutePath());

        aryHoldings = new ArrayList<>();
        for (List<String> record : records) {
            aHolding = new holding();
            for(String item : record) {
                aHolding.acctid = record.get(0);
                aHolding.stockid = record.get(1);
                aHolding.unit = Double.parseDouble(record.get(2));
                aHolding.mv = Double.parseDouble(record.get(4));
                aryHoldings.add(aHolding);
            }
        }
        return aryHoldings;
    }
}
