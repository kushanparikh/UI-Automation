package com.ui.automation.Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import com.ui.automation.Utilities.RetryAnalyzer;
import com.ui.automation.Utilities.TestNGListener;
import com.ui.automation.Utilities.ScreenshotUtil;
import com.ui.automation.Utilities.POSManagement.POSInstanceTestNGProvider;

/**
 * AppTest contains sample tests to demonstrate screenshot capture and retry logic.
 */
@Listeners(TestNGListener.class)
public class AppTest extends POSInstanceBaseTest {
    @Test(dataProvider = "posInstances", dataProviderClass = POSInstanceTestNGProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void shouldAnswerWithTrue(Integer index) {
        ScreenshotUtil.captureAndAttachScreenshot("Initial state - shouldAnswerWithTrue");
        Assert.assertTrue(true, "POS URL: " + posInstance.getPosUrl() + ", Cashier: " + posInstance.getCashierId());
        ScreenshotUtil.captureAndAttachScreenshot("After assertion - shouldAnswerWithTrue");
    }

    /**
     * A test that always fails and captures screenshots at key steps.
     */
    @Test(dataProvider = "posInstances", dataProviderClass = POSInstanceTestNGProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void shouldFailWithScreenshot(Integer index) {
        ScreenshotUtil.captureAndAttachScreenshot("Initial state - shouldFailWithScreenshot");
        Assert.assertTrue(false, "Intentional failure for screenshot demo. POS URL: " + posInstance.getPosUrl() + ", Cashier: " + posInstance.getCashierId());
        ScreenshotUtil.captureAndAttachScreenshot("After assertion - shouldFailWithScreenshot");
    }
} 