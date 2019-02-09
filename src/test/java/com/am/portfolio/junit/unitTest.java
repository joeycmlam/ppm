package com.am.portfolio.junit;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class unitTest {

    @Test
    public void Test1() {
        String actualValue = "hello";
        String expectedValue = "world";

        assertTrue(!expectedValue.equals(actualValue));
    }

    @Test
    public void Test2() {
        String actualValue = "hello";
        String expectedValue = "hello";

        assertTrue(expectedValue.equals(actualValue));
    }


}
