package com.ui.automation.pages.components;

import org.openqa.selenium.WebDriver;

public class SimulatedCustomerPanel {
    private WebDriver driver;

    public SimulatedCustomerPanel(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemVisible(String itemName) {
        // Placeholder for checking item on customer screen
        return true;
    }
} 