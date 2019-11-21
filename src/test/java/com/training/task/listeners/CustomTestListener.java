package com.training.task.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.training.task.services.DatabaseService;
import com.training.task.utils.extentreports.ExtentManager;
import com.training.task.utils.extentreports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        ExtentTestManager.startTest(iTestResult.getName(), iTestResult.getMethod().getDescription());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        DatabaseService.insertTestInfo(iTestResult.getName(), Integer.toString(iTestResult.getStatus()));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        DatabaseService.insertTestInfo(iTestResult.getName(), Integer.toString(iTestResult.getStatus()));
        ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getThrowable().toString());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
}
