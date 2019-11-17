package com.training.task.listeners;

import com.training.task.services.DatabaseService;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        DatabaseService.insertTestInfo(iTestResult.getName(), Integer.toString(iTestResult.getStatus()));
    }

    public void onTestFailure(ITestResult iTestResult) {
        DatabaseService.insertTestInfo(iTestResult.getName(), Integer.toString(iTestResult.getStatus()));
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
