package com.ui.automation.Base;

import com.ui.automation.Utilities.DriverFactory;
import com.ui.automation.pages.bo.BOLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base class for tests that only need the Back Office (BO) application.
 */
public class BOBaseTest extends BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void boSetUp() {
        driver = DriverFactory.getDriver("BO");
        BOLoginPage boLoginPage = new BOLoginPage(driver);
        boLoginPage.login(
            config.getProperty("bo.url"),
            config.getProperty("bo.username"),
            config.getProperty("bo.password")
        );
    }

    @AfterClass
    public void boTearDown() {
        DriverFactory.quitDriver("BO");
    }
} 