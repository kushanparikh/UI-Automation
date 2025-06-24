package com.ui.automation.pages.bo;

import org.openqa.selenium.WebDriver;

public class BOLoginPage {
    private WebDriver driver;

    public BOLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String url, String username, String password) {
        // Placeholder for BO login logic
        driver.get(url);
    }
} 