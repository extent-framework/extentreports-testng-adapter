package com.aventstack.extentreports.adapter.testlistenerclass;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ExtentITestListenerClassAdapter.class})
@Test(groups = "should-pass")
public class SuccessfulTests {

    @Test
    public void test() {
    }


    @Test(description = "This is a test description")
    public void testWithDescription() {
    }


    @Test
    public void testWithOutput() {
        Reporter.log("Here is some output from a successful test.");
    }


    @Test
    public void testWithMultiLineOutput() {
        Reporter.log("This is the first line of 3.");
        Reporter.log("This is a second line.");
        Reporter.log("This is the third.");
    }


    @AfterMethod
    public void afterMethod() {
        // This is here to detect any problems processing config
        // methods.
    }

    @AfterClass
    public void afterClass() {
        // This is here to detect any problems processing config
        // methods.
    }

    @AfterSuite
    public void afterSuite() {
        // This is here to detect any problems processing config
        // methods.
    }
}