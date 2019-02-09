package com.am.portfolio.test;

import com.am.portfolio.holding;
import com.am.portfolio.holdings;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;

import java.io.File;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class singleAccountFeatureTest {
    private holdings viewHolding;
    private String account_id;
    private String fileName;

    @Given("^my view account is \"([^\"]*)\" \"([^\"]*)\"$")
    public void myViewAccountIs(String acctid, String posn_file) throws Throwable {
        this.account_id = acctid;
        this.fileName = posn_file;
    }

    @When("^I call CalcEngine$")
    public void iCallCalcEngine() throws Throwable {
        viewHolding =new holdings();
        this.mockDataHolding();
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
    public void portfolioHoldingsDBIs(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.viewHolding = mock(holdings.class);
        when(this.viewHolding.getNumHoldings()).thenReturn((long) 17);
        when(this.viewHolding.numAccounts()).thenReturn(2);

    }

    @Then("^the total number of account (\\d+)$")
    public void theTotalNnumberOfAccount(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(this.viewHolding.numAccounts()==arg0);
    }
}
