package com.ui.automation.pages;

import com.ui.automation.pages.components.ItemPanel;
import com.ui.automation.pages.components.KeypadPanel;
import com.ui.automation.pages.components.PaymentPanel;
import com.ui.automation.pages.components.SimulatedCustomerPanel;
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
} 