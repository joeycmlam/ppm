package com.am.portfolio.junit;

import com.am.portfolio.holding;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class unitTest {

    @Test
    public void testTree() {


    }

    @Test
    public void Test1() {
        String actualValue = "hello";
        String expectedValue = "world";

        assertTrue(!expectedValue.equals(actualValue));
    }

    @Test
    public void Test2() {


        holding aHolding = new holding();
        aHolding.unit = 10;
        aHolding.stockid = "0001.HK";
        aHolding.acctid = "60001";
        aHolding.mv = 100;
        holding.addHolding(aHolding);

        holding bHolding = new holding();
        bHolding.unit = 20;
        bHolding.stockid = "0002.HK";
        bHolding.acctid = "60001";
        bHolding.mv = 200;
        holding.addHolding(bHolding);

        holding cHolding = new holding();
        cHolding.unit = 20;
        cHolding.stockid = "0002.HK";
        cHolding.acctid = "60001";
        cHolding.mv = 200;
        holding.addHolding(cHolding);

    }

    @Test
    public void testBigDecimal(){
        BigDecimal aValue = BigDecimal.valueOf(2);
        BigDecimal bValue = BigDecimal.valueOf(2.0);

        Assert.assertTrue((aValue.compareTo(bValue) == 0));

    }

    @Test
    public void testBigDec2() {

        BigDecimal aValue;
        BigDecimal bValue;
        BigDecimal cValuel;
        double aVal;
        double bVal;
        double cVal;
        aValue = BigDecimal.valueOf(10.5);
        bValue = BigDecimal.valueOf(9.3);
        cValuel = aValue.subtract(bValue);
        System.out.println(cValuel);

        aVal = aValue.doubleValue();
        bVal = bValue.doubleValue();
        cVal = aVal - bVal;
        System.out.println(cVal);

    }


}
