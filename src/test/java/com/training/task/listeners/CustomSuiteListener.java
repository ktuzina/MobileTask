package com.training.task.listeners;

import com.training.task.services.DatabaseService;
import com.training.task.services.DbConnectionService;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;

import java.util.Map;

public class CustomSuiteListener implements ISuiteListener {

    public void onStart(ISuite iSuite) {
        DatabaseService.insertSuiteInfo();
    }

    public void onFinish(ISuite iSuite) {
        long time = 0;
        int count = 0;
        Map<String, ISuiteResult> tests = iSuite.getResults();

        for (ISuiteResult r : tests.values()) {
            time += (r.getTestContext().getEndDate().getTime() - r.getTestContext().getStartDate().getTime()) / 1000;
            count += r.getTestContext().getAllTestMethods().length;
        }
        DatabaseService.updateSuiteInfo(count, iSuite.getName(), time);
        DbConnectionService.closeConnection();
    }
}
