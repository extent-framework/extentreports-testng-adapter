package com.aventstack.extentreports.testng.listener;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.testng.listener.commons.ExtentTestCommons;

public class ExtentITestListenerAdapter 
    implements ITestListener {

    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();
    
    @Override
    public synchronized void onStart(ITestContext context) { 
        ExtentService.getInstance().setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        ExtentService.getInstance().flush();
    }
    
    @Override
    public synchronized void onTestStart(ITestResult result) {        
        String methodName = result.getMethod().getMethodName();
        if (result.getParameters().length>0) {
            if (methodTest.get() != null && methodTest.get().getModel().getName().equals(methodName)) { } 
            else {
                createTest(result);
            }
            String paramName = Arrays.asList(result.getParameters()).toString();
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            createTest(result);
        }
    }
    
    private void createTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = ExtentService.getInstance().createTest(methodName, result.getMethod().getDescription());
        methodTest.set(test);
        
        String[] groups = result.getMethod().getGroups();
        ExtentTestCommons.assignGroups(test, groups);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        getTest(result).pass("Test passed");
    }
    
    private ExtentTest getTest(ITestResult result) {
        ExtentTest t = result.getParameters() != null && result.getParameters().length>0
                ? dataProviderTest.get()
                : methodTest.get();
        return t;
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        getTest(result).fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        getTest(result).skip(result.getThrowable());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

}
