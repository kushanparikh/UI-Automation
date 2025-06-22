package com.ui.automation.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.ui.automation.Utilities.RetryAnalyzer;
import com.ui.automation.Utilities.TestNGListener;
import com.ui.automation.Utilities.ScreenshotUtil;

/**
 * AppTest contains sample tests to demonstrate screenshot capture and retry logic.
 */
@Listeners(TestNGListener.class)
public class AppTest {
    /**
     * A test that always passes and captures screenshots at key steps.
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void shouldAnswerWithTrue() {
        ScreenshotUtil.captureAndAttachScreenshot("Initial state - shouldAnswerWithTrue");
        Assert.assertTrue(true);
        ScreenshotUtil.captureAndAttachScreenshot("After assertion - shouldAnswerWithTrue");
    }

    /**
     * A test that always fails and captures screenshots at key steps.
     */
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void shouldFailWithScreenshot() {
        ScreenshotUtil.captureAndAttachScreenshot("Initial state - shouldFailWithScreenshot");
        Assert.assertTrue(false, "Intentional failure for screenshot demo");
        ScreenshotUtil.captureAndAttachScreenshot("After assertion - shouldFailWithScreenshot");
    }
} 