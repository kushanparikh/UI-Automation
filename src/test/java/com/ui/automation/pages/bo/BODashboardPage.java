package com.ui.automation.pages.bo;

import org.openqa.selenium.WebDriver;

public class BODashboardPage {
    private final BOReportsPage reportsPage;

    public BODashboardPage(WebDriver driver) {
        this.reportsPage = new BOReportsPage(driver);
    }

    /**
     * Provides access to the reports page component.
     * @return An instance of BOReportsPage.
     */
    public BOReportsPage reports() {
        return reportsPage;
    }

    // Add other methods for interacting with the main dashboard here
} 