package com.ui.automation.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPanel {
    private WebDriver driver;

    // Locators for payment panel
    private By processPaymentButton = By.id("process_payment_button");

    public PaymentPanel(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCardDetails(String cardNumber, String expiry, String cvv) {
        // Placeholder for entering card details
    }

    public void clickProcessPayment() {
        driver.findElement(processPaymentButton).click();
    }

    public boolean isPaymentApproved() {
        // Placeholder for checking approval message
        return true;
    }
} 