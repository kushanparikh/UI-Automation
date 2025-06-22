package com.ui.automation.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportManager provides a singleton ExtentReports instance for reporting.
 */
public class ExtentReportManager {
    private static ExtentReports extent;

    /**
     * Gets the singleton ExtentReports instance, initializing it if necessary.
     * @return the ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("UI Automation Report");
            htmlReporter.config().setReportName("UI Automation Test Results");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
} 