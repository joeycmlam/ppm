package com.am.portfolio;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@RestController
@Component
public class holdings {
    private HashMap<String, HashMap<String, holding>> htHoldings;
    private nodeHolding treeHoldings;

    public int numAccounts () {
        return this.htHoldings.size();

    }

    public holdings() {
        this.htHoldings = new HashMap<>();
    }

    public void initData() {

        dataRepo dataSource = new dataRepo("src/test/resource", "input.data.01.csv");
        ArrayList<holding> records = dataSource.getPositions();
        records.forEach(aHolding -> this.addHolding(aHolding));
        this.treeHoldings = new nodeHolding(0, "Total Investment");
        for (holding aHolding : records) {
            this.treeHoldings.addNode(aHolding);
        }
        System.out.println(this.treeHoldings.currentLevel());
    }



    public holding findHolding(String accountId, String stockCode) {
        holding h = null;

        if (this.htHoldings.containsKey(accountId)) {
            if (this.htHoldings.get(accountId).containsKey(stockCode)) {
                h = this.htHoldings.get(accountId).get(stockCode);
            }
        }

        return h;
    }

    public BigDecimal getStockHolding(String stockCode){
        double dblTotalUnits = 0;
        HashMap<String, holding> aPortfolio;
        holding h;

        for (Map.Entry <String, HashMap<String, holding>> item : this.htHoldings.entrySet()){
            aPortfolio = item.getValue();
            if (aPortfolio.containsKey(stockCode)) {
                h = aPortfolio.get(stockCode);
                dblTotalUnits  += h.unit;
            }
        }
        return BigDecimal.valueOf(dblTotalUnits );
    }

    @RequestMapping("/getNumHoldings")
    public long getNumHoldings() {

        long totalNum=0;
        for (Map.Entry<String, HashMap<String, holding>> item : this.htHoldings.entrySet()) {

            HashMap<String, holding> h = item.getValue();
            totalNum += h.size();
        }
        return totalNum;
    }

    public long getNumHolding(String account_id) {
        long counter=0;

        if (this.htHoldings.containsKey(account_id)) {
            HashMap<String, holding> h = this.htHoldings.get(account_id);
          counter = h.size();
        }

        return counter;
    }

    public double getTotalMV() {
        double totalmv=0;

        for (Map.Entry<String,  HashMap<String, holding>> item : this.htHoldings.entrySet()) {

            String acctId = item.getKey();
            totalmv += this.getTotalMV(acctId);
        }

        return totalmv;
    }

    public double getTotalMV(String account_id) {
        double totalmv=0;

        if (this.htHoldings.containsKey(account_id)) {
            HashMap<String, holding> lstHoldings = this.htHoldings.get(account_id);
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

        HashMap<String, holding> lstHoldings;
        if (this.htHoldings.containsKey(h.acctid)) {
            lstHoldings = this.htHoldings.get(h.acctid);
        } else {
            lstHoldings =new HashMap<>();
            this.htHoldings.put(h.acctid, lstHoldings);
        }
        lstHoldings.put(h.stockid, h);


    }

    public void calcWeight(String account_id) {
        double totalMV = this.getTotalMV(account_id);

        if (this.htHoldings.containsKey(account_id)) {
            HashMap<String, holding> lstHolding = this.htHoldings.get(account_id);
            for (holding h : lstHolding.values()) {
                h.wgt = BigDecimal.valueOf(h.mv / totalMV);
            }
        } else {
            //do nothing
        }
    }

    public void calcWeight() {

        for (Map.Entry<String, HashMap<String, holding>> item : this.htHoldings.entrySet()) {
            String acctId = item.getKey();
            this.calcWeight(acctId);
        }
    }

    public holding getHolding(String account_id, String stock_code) {
        holding h = null;

        if (this.htHoldings.containsKey(account_id)) {
            HashMap<String, holding> acctHolding = this.htHoldings.get(account_id);
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
