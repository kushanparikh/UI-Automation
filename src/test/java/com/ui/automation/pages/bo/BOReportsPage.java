package com.ui.automation.pages.bo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BOReportsPage {
    private WebDriver driver;

    // Locators for the reports page
    private By reportsMenu = By.id("menu_reports");
    private By reconciliationSubMenu = By.id("submenu_reconciliation");
    private By salesTotalElement = By.id("sales_total");

    public BOReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * A high-level business method that encapsulates multiple UI steps.
     * @return The current sales total as a string.
     */
    public String getTodaysSalesTotal() {
        driver.findElement(reportsMenu).click();
        driver.findElement(reconciliationSubMenu).click();
        return driver.findElement(salesTotalElement).getText();
    }
} 