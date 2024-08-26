package org.atg.assignment.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atg.assignment.drivermanager.ChromeDriverManager;
import org.atg.assignment.drivermanager.FireFoxDriverManager;
import org.atg.assignment.enums.DriverType;
import org.atg.assignment.util.PageObjectUtil;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class WebDriverFactory {

    private static final Logger LOG = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver getWebDriver(DriverType driverType) {
        LOG.info("Get WebDriver for driverType: {}", driverType);

        WebDriver webDriver;
        if (Objects.requireNonNull(driverType) == DriverType.FIREFOX) {
            webDriver = new FireFoxDriverManager().createWebDriver();
        } else {
            webDriver = new ChromeDriverManager().createWebDriver();
        }
        PageObjectUtil.maximizeWindow(webDriver);
        LOG.info("Returning WebDriver: {}", webDriver);
        return webDriver;
    }
}
