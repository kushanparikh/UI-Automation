package com.ui.automation.Tests;

import com.ui.automation.Utilities.POSManagement.POSInstance;
import com.ui.automation.Utilities.POSManagement.POSInstanceManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public abstract class POSInstanceBaseTest {
    protected POSInstance posInstance;
    protected POSInstanceManager posManager;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws InterruptedException {
        posManager = POSInstanceManager.getInstance();
        posInstance = posManager.acquireInstance();
        System.out.println("[Thread " + Thread.currentThread().getId() + "] Acquired: " + posInstance);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (posInstance != null && posManager != null) {
            posManager.releaseInstance(posInstance);
            System.out.println("[Thread " + Thread.currentThread().getId() + "] Released: " + posInstance);
        }
    }
} 