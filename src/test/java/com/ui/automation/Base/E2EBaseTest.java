package com.ui.automation.Base;

import com.ui.automation.Utilities.DriverFactory;
import com.ui.automation.pages.bo.BOLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base class for end-to-end tests that require both POS and BO applications.
 */
public class E2EBaseTest extends POSBaseTest {
    protected WebDriver boDriver;

    @BeforeClass
    public void e2eSetUp() {
        // POS setup is automatically called from the parent class (POSBaseTest)
        boDriver = DriverFactory.getDriver("BO");
        BOLoginPage boLoginPage = new BOLoginPage(boDriver);
        boLoginPage.login(
            config.getProperty("bo.url"),
            config.getProperty("bo.username"),
            config.getProperty("bo.password")
        );
    }

    @AfterClass
    public void e2eTearDown() {
        // The parent @AfterClass (posTearDown) will be called automatically
        DriverFactory.quitDriver("BO");
    }
} 