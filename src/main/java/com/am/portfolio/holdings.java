package com.am.portfolio;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;



public class holdings {
    private Hashtable<String, Hashtable<String, holding>> htHoldings;
    

    public int numAccounts () {
        return this.htHoldings.size();

    }

    public holdings() {
        this.htHoldings = new Hashtable<>();
    }

    public void initData() {
        dataRepo dataSource = new dataRepo();
        String response = dataSource.getPosition();





    }

    public long getNumHoldings() {

        long totalNum=0;
        for (Map.Entry<String, Hashtable<String, holding>> item : this.htHoldings.entrySet()) {

            Hashtable<String, holding> h = item.getValue();
            totalNum += h.size();
        }
        return totalNum;
    }

    public long getNumHolding(String account_id) {
        long counter=0;

        if (this.htHoldings.containsKey(account_id)) {
          Hashtable<String, holding> h = this.htHoldings.get(account_id);
          counter = h.size();
        }

        return counter;
    }

    public double getTotalMV() {
        double totalmv=0;

        for (Map.Entry<String,  Hashtable<String, holding>> item : this.htHoldings.entrySet()) {

            String acctId = item.getKey();
            totalmv += this.getTotalMV(acctId);
        }

        return totalmv;
    }

    public double getTotalMV(String account_id) {
        double totalmv=0;

        if (this.htHoldings.containsKey(account_id)) {
            Hashtable<String, holding> lstHoldings = this.htHoldings.get(account_id);
            for (String stockCode : lstHoldings.keySet()) {
                holding h = lstHoldings.get(stockCode);
                totalmv += h.mv;
            }
//            totalmv = lstHoldings.stream().mapToDouble(h -> h.mv).sum();
        }
        return totalmv;
    }

    public void addHolding(holding h) {
        this.addHolding(h.acctid, h.stockid, h.unit, h.mv);
    }

    public void addHolding(String account_id, String stock_code, double unit, double mv) {
        holding h = new holding();
        h.acctid = account_id;
        h.stockid = stock_code;
        h.unit = unit;
        h.mv = mv;

        Hashtable<String, holding> lstHoldings;
        if (this.htHoldings.containsKey(h.acctid)) {
            lstHoldings = this.htHoldings.get(h.acctid);
        } else {
            lstHoldings =new Hashtable<>();
            this.htHoldings.put(h.acctid, lstHoldings);
        }
        lstHoldings.put(h.stockid, h);
    }

    public void calcWeight(String account_id) {
        double totalMV = this.getTotalMV(account_id);

        if (this.htHoldings.containsKey(account_id)) {
            Hashtable<String, holding> lstHolding = this.htHoldings.get(account_id);
            for (holding h : lstHolding.values()) {
                h.wgt = BigDecimal.valueOf(h.mv / totalMV);
            }
        } else {
            //do nothing
        }
    }

    public void calcWeight() {

        for (Map.Entry<String, Hashtable<String, holding>> item : this.htHoldings.entrySet()) {
            String acctId = item.getKey();
            this.calcWeight(acctId);
        }
    }

    public holding getHolding(String account_id, String stock_code) {
        holding h = null;

        if (this.htHoldings.containsKey(account_id)) {
            Hashtable<String, holding> acctHolding = this.htHoldings.get(account_id);
            if (acctHolding.containsKey(stock_code)){
                h = acctHolding.get(stock_code);
            } else {
                //do nothing
            }
        } else {
            //do nothing
        }

        return h;
    }
}
