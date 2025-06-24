package com.ui.automation.pages.pos.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KeypadPanel {
    private WebDriver driver;

    // Locators for the keypad
    private By totalButton = By.id("keypad_total_button");

    public KeypadPanel(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTotal() {
        driver.findElement(totalButton).click();
    }
} 