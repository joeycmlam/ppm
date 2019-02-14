package com.am.portfolio.test;

import com.am.portfolio.holding;
import com.am.portfolio.holdings;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;

import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class singleAccountFeatureTest {
    private holdings viewHolding;

    private String account_id;
    private String fileName;
    private String stock_code;

    @Given("^my view account is \"([^\"]*)\" \"([^\"]*)\"$")
    public void myViewAccountIs(String acctid, String posn_file) throws Throwable {
        this.account_id = acctid;
        this.fileName = posn_file;
    }

    @When("^I call CalcEngine$")
    public void iCallCalcEngine() throws Throwable {
        viewHolding =new holdings();
        viewHolding.initData();;
//        this.mockDataHolding();
        this.viewHolding.calcWeight();
//        this.mockitoHoldings();
    }

    @Then("^I should get Number Holding \"([^\"]*)\"$")
    public void iShouldGet(long expNumHolding) throws Throwable {
        long actualNumHolding = viewHolding.getNumHolding(this.account_id);
        Assert.assertEquals(expNumHolding, actualNumHolding);
    }

    @Then("^I should get MV Report \"([^\"]*)\"$")
    public void iShouldGetMVReport(double expMV) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        double actualMV = viewHolding.getTotalMV(this.account_id);
        Assert.assertEquals(expMV, actualMV, 0);
    }

    private void mockHoldings() throws Throwable{
        this.viewHolding = mock(holdings.class);

    }

    private void mockitoHoldings() throws Throwable {
        this.viewHolding = mock(holdings.class);
        when(this.viewHolding.getNumHolding("660001")).thenReturn((long) 9);
        when(this.viewHolding.getNumHolding("670001")).thenReturn((long) 7);
        when(this.viewHolding.getTotalMV("660001")).thenReturn((double) 6960.0);
        when(this.viewHolding.getTotalMV("670001")).thenReturn((double) 6090.0);
    }

    private void mockDataHolding() throws Throwable {

        holding h;
        try {
            //Get file from resources folder
            File file  = new File("src/test/resource", this.fileName);

            String[] items;
            try (Scanner buff = new Scanner(file)) {

                String headerLine = buff.nextLine(); //skip header line

                while (buff.hasNextLine()) {
                    String line = buff.nextLine();
                    items = line.split(",");
                    h = new holding();
                    h.acctid = items[0];
                    h.stockid = items[1];
                    h.unit = Long.parseLong(items[2]);
                    h.mv = Double.parseDouble(items[4]);
                    this.viewHolding.addHolding(h);
                }

                buff.close();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            throw ex;
        }
    }

    @Given("^portfolio holdings DB is \"([^\"]*)\"$")
    public void portfolioHoldingsDBIs(String posn_file) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.fileName = posn_file;
    }

    @Then("^the total number of account (\\d+)$")
    public void theTotalNnumberOfAccount(int expectedNumAcct) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(this.viewHolding.numAccounts()==expectedNumAcct);
    }

    @Given("^portfolio holdings DB by mock object$")
    public void portfolioHoldingsDBByMockObject() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.viewHolding = mock(holdings.class);
        when(this.viewHolding.getNumHoldings()).thenReturn((long) 17);
        when(this.viewHolding.numAccounts()).thenReturn(5);
    }

    @Then("^the total number of account (\\d+) number holding (\\d+)$")
    public void theTotalNumberOfAccountNumberHolding(int expectedNumAcct, int expectedHolding) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(expectedNumAcct, this.viewHolding.numAccounts());
        Assert.assertEquals(expectedHolding, this.viewHolding.getNumHoldings());
    }


    @Given("^Source data \"([^\"]*)\" and view account is \"([^\"]*)\" \"([^\"]*)\"$")
    public void sourceDataAndViewAccountIs(String posn_file, String account_id, String stockCode) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.account_id = account_id;
        this.fileName = posn_file;
        this.stock_code = stockCode;
    }

    @Then("^I should get portfolio weight \"([^\"]*)\"$")
    public void iShouldGetPortfolioWeight(BigDecimal expectedValue) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        holding h = this.viewHolding.getHolding(this.account_id, this.stock_code);
        BigDecimal actualValue = h.wgt.setScale(5,BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedDecimalValue = expectedValue.setScale(5, BigDecimal.ROUND_HALF_UP);
        Assert.assertEquals(expectedDecimalValue, actualValue);

    }
}
