package com.aventstack.extentreports.adapter.testlistener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MyRetryAnalyzer;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;

@Listeners({ExtentITestListenerAdapter.class})
public class Retries {

    @Test(retryAnalyzer = MyRetryAnalyzer.class)
    public void testSum() {
        Assert.assertTrue(false);
    }

}
