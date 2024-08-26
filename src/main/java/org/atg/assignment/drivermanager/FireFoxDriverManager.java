package org.atg.assignment.drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverManager {

    private static final Logger LOG = LogManager.getLogger(FireFoxDriverManager.class);

    public WebDriver createWebDriver() {
        try {
            LOG.info("Setting up FireFox driver");
            final WebDriverManager fireFoxDriver = WebDriverManager.firefoxdriver();
            fireFoxDriver.setup();
            WebDriver driver = new FirefoxDriver();
            LOG.info("FireFox Driver setup successfully");
            return driver;
        } catch (Exception e) {
            LOG.error("Exception setting FireFox driver", e);
            throw e;
        }
    }
}