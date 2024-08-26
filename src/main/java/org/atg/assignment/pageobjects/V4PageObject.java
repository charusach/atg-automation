package org.atg.assignment.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

public class V4PageObject {

    @FindBy(css = "[data-test-id=\"calendar-next-day-button\"]")
    private WebElement calendarNextDayButton;

    @FindBy(css = "[data-test-id=\"calendar-menu-gameType-V4\"]")
    private WebElement gameTypeV4Button;

    @FindBy(css = "[data-test-id=\"MoreHorizontalIcon\"]")
    private WebElement moreHorizontalLink;

    @FindBy(css = "[id=\"composition-menu\"]")
    private WebElement moreLinkList;

    @FindBy(css = "[value=\"new-coupon\"]")
    private WebElement newCouponLink;

    @FindBy(xpath = "//button[text()=\"Bekräfta\"]")
    private WebElement confirmButton;

    @FindBy(css = "[data-start-number]")
    private List<WebElement> horseStartNumber;

    @FindBy(css = "[data-test-id$=\"-menu-info\"]")
    private List<WebElement> raceMenuLegs;


    @FindBy(css = "[data-test-id=\"play-game-coupon\"]")
    private WebElement playGameButton;


    public V4PageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getCalendarNextDayButton() {
        return calendarNextDayButton;
    }

    public WebElement getGameTypeV4Button() {
        return gameTypeV4Button;
    }

    public WebElement getMoreHorizontalLink() {
        return moreHorizontalLink;
    }

    public WebElement getMoreLinkItemByValue(String value) {
        return moreLinkList.findElement(By.cssSelector("li[value=" + value + "]"));
    }

    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public Optional<WebElement> getHorseNumberByValue(String value) {
        return horseStartNumber.stream()
                .filter(h -> h.getAttribute("data-start-number").equals(value))
                .findFirst();
    }

    public Optional<WebElement> getRaceMenuLegByValue(int value) {
        return raceMenuLegs.stream()
                .filter(h -> h.getAttribute("data-test-id").equals("race-" + value + "-menu-info"))
                .findFirst();
    }

    public WebElement getPlayGameButton() {
        return playGameButton;
    }

    public int getTotalHorseCount() {
        return horseStartNumber.size();
    }
}
