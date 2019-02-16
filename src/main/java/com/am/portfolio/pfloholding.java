package com.am.portfolio;

import java.util.Hashtable;
import java.util.Map;

public class pfloholding {

    private String accountId;
    private Hashtable<String, holding> htHoldings;
    private double nav;

    public pfloholding() {
        this.accountId = "";
        this.htHoldings = new Hashtable<>();
        this.nav = 0.0;
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

        this.htHoldings.put(h.stockid, h);

    }

    public void calcProcess() {
        this.nav = 0.0;
        for (Map.Entry<String, holding> item : this.htHoldings.entrySet()) {
            holding h = item.getValue();
            this.nav += h.mv;
        }
    }

}
