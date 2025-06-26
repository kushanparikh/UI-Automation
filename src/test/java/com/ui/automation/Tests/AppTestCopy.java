package com.ui.automation.Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import com.ui.automation.Utilities.RetryAnalyzer;
import com.ui.automation.Utilities.TestNGListener;
import com.ui.automation.Utilities.POSManagement.POSInstanceTestNGProvider;

@Listeners(TestNGListener.class)
public class AppTestCopy extends POSInstanceBaseTest {
    @Test(dataProvider = "posInstances", dataProviderClass = POSInstanceTestNGProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void printPOSInstanceDetails(Integer index) {
        System.out.println("[Thread " + Thread.currentThread().getId() + "] POSInstance: " + posInstance);
        Assert.assertNotNull(posInstance.getPosUrl(), "POS URL should not be null");
    }
} 