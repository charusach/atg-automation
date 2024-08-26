package org.atg.assignment.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class PageObjectUtil {

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public static void waitUntilVisible(WebDriver driver, WebElement element) throws IOException {
        waitForCondition(driver, ExpectedConditions.visibilityOf(element),
                PropertyManager.getInstance().getExplicitWaitTime());
    }

    private static <T> void waitForCondition(WebDriver driver, ExpectedCondition<T> condition, Long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(condition);
    }
}
