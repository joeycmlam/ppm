package com.am.portfolio;

import java.util.ArrayList;
import java.util.List;

public class holdings {
    private List<holding> acctHoldings;

    public holdings() {
        this.acctHoldings = new ArrayList<>();
    }

    public long getNumHolding() {
        return acctHoldings.size();
    }

    public long getNumHolding(String account_id) {
        long counter=0;
        for (int i=0; acctHoldings.size()>i; i++) {
            holding h = acctHoldings.get(i);
            if (account_id.equals(h.acctid)) {
                counter++;
            }
        }
        return counter;
    }

    public void loadData(String resp) {
        holding h = new holding();
        h.acctid= "660001";
        h.stockid = "0001.HK";
        h.unit = 1000;
        h.mv = 1500;
        this.addHolding(h);

        h = new holding();
        h.acctid= "660001";
        h.stockid = "0002.HK";
        h.unit = 2000;
        h.mv = 1800;
        this.addHolding(h);

        h = new holding();
        h.acctid= "670001";
        h.stockid = "0002.HK";
        h.unit = 1500;
        h.mv = 1300;
        this.addHolding(h);
    }

    public double getTotalMV() {
        double totalmv=0;
        for (int i=0; acctHoldings.size()>i; i++) {
            holding h = acctHoldings.get(i);
            totalmv += h.mv;
        }
        return totalmv;
    }

    public double getTotalMV(String account_id) {
        double totalmv=0;
        for (int i=0; acctHoldings.size()>i; i++) {
            holding h = acctHoldings.get(i);
            if (account_id.equals(h.acctid)) {
                totalmv += h.mv;
            }
        }
        return totalmv;
    }

    public void addHolding(holding h) {
        this.acctHoldings.add(h);
    }

    public void addHolding(String account_id, String stock_code, double unit, double mv) {
        holding h = new holding();
        h.acctid = account_id;
        h.stockid = stock_code;
        h.unit = unit;
        h.mv = mv;
        acctHoldings.add(h);
    }
}
