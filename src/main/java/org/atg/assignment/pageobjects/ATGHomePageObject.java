package org.atg.assignment.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ATGHomePageObject {

    @FindBy(css = "[data-test-id=\"acceptAllCookiesBtn\"]")
    private WebElement acceptAllCookiesButton;
    @FindBy(css = "[data-test-id=\"HorseOutlinedIcon\"]")
    private WebElement horseBettingLink;
    @FindBy(css = "[data-test-id=\"quicklinks-calendar-horsebetting\"]")
    private WebElement hoseBettingCalendarLink;

    public ATGHomePageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getAcceptAllCookiesButton() {
        return acceptAllCookiesButton;
    }

    public WebElement getHorseBettingLink() {
        return horseBettingLink;
    }

    public WebElement getHoseBettingCalendarLink() {
        return hoseBettingCalendarLink;
    }
}
