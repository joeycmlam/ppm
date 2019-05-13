package com.am.portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class holding  {



    public String country="";
    public String region="";
    public String sector="";
    public String acctid="";
    public String stockid="";
    public double unit=0;
    public double mv=0;
    public BigDecimal wgt= BigDecimal.valueOf(0.0);
    public boolean isHide=false;

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }


    public static void addHolding(holding aHolding) {
        List<holding> lstHolding;

        lstHolding = new ArrayList<>();

        if (lstHolding.contains(aHolding)) {
            //is existing, do nothing
        } else {
            lstHolding.add(aHolding);
        }
    }

}
