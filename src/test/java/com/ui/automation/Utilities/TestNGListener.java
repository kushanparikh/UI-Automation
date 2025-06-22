package com.ui.automation.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ui.automation.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNGListener implements ITestListener to provide reporting and screenshot capture on test events.
 */
public class TestNGListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentReportManager.getInstance();

    /**
     * Called when a test starts.
     * @param result the test result
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    /**
     * Called when a test passes.
     * @param result the test result
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
    }

    /**
     * Called when a test fails. Captures and attaches a screenshot.
     * @param result the test result
     */
    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable());
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshotOnFailure(driver, result.getMethod().getMethodName());
            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    /**
     * Called when a test is skipped.
     * @param result the test result
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, result.getThrowable());
    }

    /**
     * Called when all tests have finished.
     * @param context the test context
     */
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    /**
     * Gets the current thread's ExtentTest instance.
     * @return the ExtentTest instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }
} 