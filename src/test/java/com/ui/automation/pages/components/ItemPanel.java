package com.ui.automation.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPanel {
    private WebDriver driver;

    // Locators for the item panel
    private By simulatedScannerInput = By.id("simulated_scanner_input");
    private By simulatedScanButton = By.id("simulated_scan_button");

    public ItemPanel(WebDriver driver) {
        this.driver = driver;
    }

    public void scanItemWithButton(String itemId) {
        driver.findElement(simulatedScannerInput).sendKeys(itemId);
        driver.findElement(simulatedScanButton).click();
    }

    public boolean isItemVisible(String itemName) {
        // Placeholder for logic to check if item is in the list
        return true;
    }
} 