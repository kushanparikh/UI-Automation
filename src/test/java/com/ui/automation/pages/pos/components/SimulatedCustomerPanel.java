package com.ui.automation.pages.pos.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimulatedCustomerPanel {
    private WebDriver driver;

    public SimulatedCustomerPanel(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemVisible(String itemName) {
        // Example check: finds an element containing the item's name.
        // This will need to be adjusted to your application's actual HTML.
        return !driver.findElements(By.xpath("//*[contains(text(), '" + itemName + "')]")).isEmpty();
    }
} 