package com.ui.automation.Utilities;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil provides utility methods for capturing and attaching screenshots in reports.
 * <p>
 * <b>Design Note:</b>
 * <ul>
 *   <li>
 *     <b>captureAndAttachScreenshot</b> is intended for use within test code to capture and immediately attach a screenshot
 *     to the Extent report at any step, with a custom description. This is useful for documenting or debugging specific steps,
 *     regardless of test outcome.
 *   </li>
 *   <li>
 *     <b>captureScreenshotOnFailure</b> is intended for use by listeners (such as TestNGListener) to capture a screenshot on test failure.
 *     It returns the file path, allowing the listener to attach the screenshot specifically to the failure log in the report.
 *   </li>
 * </ul>
 * This separation provides flexibility: automated failure screenshots and manual step-by-step documentation.
 */
public class ScreenshotUtil {
    /**
     * Captures a screenshot and attaches it to the Extent report with a step description.
     * Intended for use within test code for step-by-step documentation or debugging.
     *
     * @param stepDescription description of the step for the screenshot
     */
    public static void captureAndAttachScreenshot(String stepDescription) {
        WebDriver driver = DriverFactory.getDriver();
        ExtentTest extentTest = TestNGListener.getTest();
        if (driver == null || extentTest == null) return;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destPath = "test-output/screenshots/" + stepDescription.replaceAll("\\s+", "_") + "_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("test-output/screenshots/"));
            Files.copy(srcFile.toPath(), Paths.get(destPath));
            extentTest.info(stepDescription).addScreenCaptureFromPath(destPath);
        } catch (IOException e) {
            extentTest.warning("Failed to attach screenshot: " + e.getMessage());
        }
    }

    /**
     * Captures a screenshot on test failure and returns the file path.
     * Intended for use by listeners (e.g., TestNGListener) to attach the screenshot to the failure log.
     *
     * @param driver the WebDriver instance
     * @param testName the name of the test
     * @return the file path of the screenshot, or null if failed
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destPath = "test-output/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("test-output/screenshots/"));
            Files.copy(srcFile.toPath(), Paths.get(destPath));
            return destPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
} 