package com.ui.automation.Tests;

import com.ui.automation.Base.E2EBaseTest;
import com.ui.automation.Utilities.DatabaseUtil;
import com.ui.automation.pages.bo.BODashboardPage;
import com.ui.automation.pages.pos.POSTransactionPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class E2ESalesTest extends E2EBaseTest {

    @BeforeClass
    public void setUpDBUtilConfig() {
        DatabaseUtil.setConfig(config);
    }

    @Test
    public void testPOSTransactionUpdatesBOReconciliation() {
        // 1. ARRANGE: Get the initial state from the Back Office.
        BODashboardPage boDashboard = new BODashboardPage(boDriver);
        String preSaleAmount = boDashboard.reports().getTodaysSalesTotal();
        System.out.println("Pre-Sale Amount: " + preSaleAmount);

        // 2. ACT: Perform the transaction in the Point of Sale application.
        POSTransactionPage posPage = new POSTransactionPage(driver);
        // Assume this method returns the transaction ID
        String transactionId = posPage.itemPanel().completeSaleAndGetTransactionId("ITEM123");

        // 3. SYNCHRONIZE: Wait for the back-end to process the transaction.
        DatabaseUtil.waitForTransactionStatus(transactionId, 2, 15); // Wait up to 15s for status 2

        // 4. ASSERT: Verify the new state in the Back Office.
        String postSaleAmount = boDashboard.reports().getTodaysSalesTotal();
        System.out.println("Post-Sale Amount: " + postSaleAmount);

        Assert.assertNotEquals(preSaleAmount, postSaleAmount, "The sales amount should have changed after the transaction.");
    }
} 