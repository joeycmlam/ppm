package com.am.portfolio.test;

import com.am.portfolio.holdings;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;


public class singleAccountFeatureTest {
    private holdings viewHolding;
    private String account_id;

    @Given("^my view account is \"([^\"]*)\"$")
    public void myViewAccountIs(String acctid) throws Throwable {
        this.account_id = acctid;
    }

    @When("^I call CalcEngine$")
    public void iCallCalcEngine() throws Throwable {
        viewHolding =new holdings();
        viewHolding.loadData("test");
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
}
