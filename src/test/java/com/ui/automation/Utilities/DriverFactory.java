package com.ui.automation.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * DriverFactory manages a thread-safe map of named WebDriver instances for parallel execution.
 */
public class DriverFactory {
    private static final ThreadLocal<Map<String, WebDriver>> driverMap = ThreadLocal.withInitial(HashMap::new);

    /**
     * Gets or creates a named WebDriver instance for the current thread.
     * @param driverName A unique name for the driver (e.g., "POS", "BO").
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver(String driverName) {
        if (driverMap.get().get(driverName) == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
            }
            driverMap.get().put(driverName, new ChromeDriver(options));
        }
        return driverMap.get().get(driverName);
    }

    /**
     * Quits a specific named WebDriver instance for the current thread.
     * @param driverName The name of the driver to quit.
     */
    public static void quitDriver(String driverName) {
        WebDriver driver = driverMap.get().get(driverName);
        if (driver != null) {
            driver.quit();
            driverMap.get().remove(driverName);
        }
    }

    /**
     * Quits all WebDriver instances for the current thread.
     */
    public static void quitAllDrivers() {
        driverMap.get().values().forEach(WebDriver::quit);
        driverMap.get().clear();
    }
} 