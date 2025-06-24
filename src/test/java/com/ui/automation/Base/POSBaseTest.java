package com.ui.automation.Base;

import com.ui.automation.Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base class for tests that only need the POS application.
 */
public class POSBaseTest extends BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void posSetUp() {
        driver = DriverFactory.getDriver("POS");
        // Perform login on the POS screen
        // Note: For a real app, you would use a dedicated LoginPage class
        driver.get(config.getProperty("pos.url"));
    }

    @AfterClass
    public void posTearDown() {
        DriverFactory.quitDriver("POS");
    }
} 