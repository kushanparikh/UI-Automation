package com.ui.automation.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ui.automation.Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * BaseTest provides setup and teardown for all UI automation tests.
 */
public class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected WebDriver driver;
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
    }

    /**
     * Flushes the Extent report after the test suite finishes.
     */
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) extent.flush();
    }

    /**
     * Loads configuration properties from src/test/resources/config.properties.
     */
    protected void loadConfig() {
        config = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                config.load(input);
            } else {
                throw new IOException("config.properties not found in resources");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Logs in to the application using credentials from config.properties.
     * Assumes standard input fields with IDs: 'username', 'password', and a button with ID 'loginBtn'.
     */
    protected void login() {
        String url = config.getProperty("url");
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        driver.get(url);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();
    }

    /**
     * Initializes WebDriver, loads config, and logs in ONCE per test class.
     */
    @BeforeClass
    public void classSetUp() {
        loadConfig();
        driver = DriverFactory.getDriver();
        login();
    }

    /**
     * Quits WebDriver ONCE per test class.
     */
    @AfterClass
    public void classTearDown() {
        DriverFactory.quitDriver();
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