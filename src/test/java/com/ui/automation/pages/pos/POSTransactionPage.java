package com.ui.automation.pages.pos;

import com.ui.automation.pages.pos.components.ItemPanel;
import com.ui.automation.pages.pos.components.KeypadPanel;
import com.ui.automation.pages.pos.components.PaymentPanel;
import com.ui.automation.pages.pos.components.SimulatedCustomerPanel;
import org.openqa.selenium.WebDriver;

public class POSTransactionPage {
    private final ItemPanel itemPanel;
    private final KeypadPanel keypadPanel;
    private final PaymentPanel paymentPanel;
    private final SimulatedCustomerPanel customerPanel;

    public POSTransactionPage(WebDriver driver) {
        this.itemPanel = new ItemPanel(driver);
        this.keypadPanel = new KeypadPanel(driver);
        this.paymentPanel = new PaymentPanel(driver);
        this.customerPanel = new SimulatedCustomerPanel(driver);
    }

    public ItemPanel itemPanel() {
        return itemPanel;
    }

    public KeypadPanel keypadPanel() {
        return keypadPanel;
    }

    public PaymentPanel paymentPanel() {
        return paymentPanel;
    }

    public SimulatedCustomerPanel customerPanel() {
        return customerPanel;
    }

    /**
     * Scans an item, completes the sale, and returns the transaction ID.
     * Adjust the implementation to match your application's actual flow.
     */
    public String completeSaleAndGetTransactionId(String itemId) {
        itemPanel.scanItemWithButton(itemId);
        // Add logic to complete the sale, e.g., click total, process payment, etc.
        // keypadPanel.clickTotal();
        // paymentPanel.clickProcessPayment();
        // Retrieve the transaction ID from the UI (replace with actual logic)
        String transactionId = "mock-txn-id"; // TODO: Replace with real retrieval logic
        return transactionId;
    }
} 