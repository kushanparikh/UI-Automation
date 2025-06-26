package com.ui.automation.Utilities.POSManagement;

import org.testng.annotations.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class POSInstanceTestNGProvider {

    @DataProvider(name = "posInstances", parallel = true)
    public static Iterator<Object[]> posInstancesProvider() {
        POSInstanceManager manager = POSInstanceManager.getInstance();
        int poolSize = manager.getPoolSize();
        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            data.add(new Object[] { i }); // Pass index, not instance, to allow proper acquire/release
        }
        return data.iterator();
    }

    public static class POSInstanceTest {
        private POSInstance posInstance;
        private POSInstanceManager manager;

        @BeforeClass(alwaysRun = true)
        public void setUp() throws InterruptedException {
            manager = POSInstanceManager.getInstance();
            posInstance = manager.acquireInstance();
            System.out.println("[Thread " + Thread.currentThread().getId() + "] Acquired: " + posInstance);
        }

        @Test(dataProvider = "posInstances", dataProviderClass = POSInstanceTestNGProvider.class)
        public void testPOSInstance(Integer index) {
            // Use posInstance for your test logic
            System.out.println("[Thread " + Thread.currentThread().getId() + "] Testing with: " + posInstance);
            // Example: Assert POS URL is not null
            assert posInstance.getPosUrl() != null;
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() {
            if (posInstance != null && manager != null) {
                manager.releaseInstance(posInstance);
                System.out.println("[Thread " + Thread.currentThread().getId() + "] Released: " + posInstance);
            }
        }
    }
} 