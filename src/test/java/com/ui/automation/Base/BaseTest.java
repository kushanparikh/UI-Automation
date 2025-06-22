package com.ui.automation.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ui.automation.Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;

/**
 * BaseTest provides setup and teardown for all UI automation tests.
 */
public class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected WebDriver driver;

    /**
     * Initializes the Extent report before the test suite starts.
     */
    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("UI Automation Report");
        htmlReporter.config().setReportName("UI Automation Test Results");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    /**
     * Flushes the Extent report after the test suite finishes.
     */
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) extent.flush();
    }

    /**
     * Sets up the WebDriver and ExtentTest before each test method.
     * @param method the test method
     */
    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverFactory.getDriver();
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    /**
     * Handles test result logging and driver cleanup after each test method.
     * @param result the test result
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip(result.getThrowable());
        }
        DriverFactory.quitDriver();
    }
} 