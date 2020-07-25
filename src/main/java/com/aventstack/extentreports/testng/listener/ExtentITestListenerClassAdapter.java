package com.aventstack.extentreports.testng.listener;

import org.testng.*;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.service.ExtentTestManager;

public class ExtentITestListenerClassAdapter implements ITestListener, IInvokedMethodListener {

    private Boolean createdMethodBeforeInvocation = false;

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
        if (!createdMethodBeforeInvocation)
            ExtentTestManager.createMethod(result, true);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTestManager.log(result, true);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentTestManager.log(result, true);
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        if (result.wasRetried()) {
            ExtentService.getInstance().removeTest(result.getName());
        } else
            ExtentTestManager.log(result);
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestMethod().isBeforeMethodConfiguration()) {
            createdMethodBeforeInvocation = true;
            ExtentTestManager.createMethod(testResult, true);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }
}
