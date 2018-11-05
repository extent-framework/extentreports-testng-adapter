package com.aventstack.extentreports.testng.listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.testng.listener.commons.ExtentTestCommons;

public class ExtentITestListenerClassAdapter 
    implements ITestListener {

    private static Map<String, ExtentTest> classTestMap = new HashMap<>();
    private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();
    
    @Override
    public synchronized void onStart(ITestContext context) { 
        ExtentService.getInstance().setAnalysisStrategy(AnalysisStrategy.CLASS);
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        ExtentService.getInstance().flush();
    }
    
    @Override
    public synchronized void onTestStart(ITestResult result) {
        String className = result.getInstance().getClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        ExtentTest classTest;
        
        if (classTestMap.containsKey(className)) {
            classTest = classTestMap.get(className);
        } else {
            classTest = ExtentService.getInstance().createTest(className);
            classTestMap.put(className, classTest);
        }

        Optional<Test> test = classTest.getModel()
            .getNodeContext().getAll()
            .stream()
            .filter(x -> x.getName().equals(methodName))
            .findFirst();
        
        if (result.getParameters().length>0) {
            if (!test.isPresent()) {
                createNode(classTest, result);
            }
            String paramName = Arrays.asList(result.getParameters()).toString();
            ExtentTest paramTest = methodTest.get().createNode(paramName);
            dataProviderTest.set(paramTest);
        } else {
            dataProviderTest.set(null);
            createNode(classTest, result);
        }
    }

    private void createNode(ExtentTest classTest, ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest child = classTest.createNode(methodName, result.getMethod().getDescription());
        methodTest.set(child);
        
        String[] groups = result.getMethod().getGroups();
        ExtentTestCommons.assignGroups(child, groups);
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
