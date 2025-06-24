package com.ui.automation.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * The root of the test hierarchy. Manages reporting and configuration, but does not initialize any WebDrivers.
 */
public abstract class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected Properties config;

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
        loadConfig();
    }

    /**
     * Flushes the Extent report after the test suite finishes.
     */
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Loads configuration properties from src/test/resources/config.properties.
     */
    protected void loadConfig() {
        config = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("config.properties not found in resources");
            }
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Sets up ExtentTest for reporting before each test method.
     * @param method the test method
     */
    @BeforeMethod
    public void setUp(Method method) {
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    /**
     * Handles test result logging after each test method.
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
    }
} 