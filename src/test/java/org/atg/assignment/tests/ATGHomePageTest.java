package org.atg.assignment.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atg.assignment.pageobjects.ATGHomePageObject;
import org.atg.assignment.pageobjects.V4PageObject;
import org.atg.assignment.util.PageObjectUtil;
import org.atg.assignment.util.PropertyManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATGHomePageTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(ATGHomePageTest.class);

    @DisplayName("Verify V4 horse racing test")
    @Test
    public void verifyV4HorseRacingTest() throws IOException {
        LOG.info("Load ATG home page");
        ATGHomePageObject atgHomePageObject = new ATGHomePageObject(driver);
        LOG.info("ATG Home page loaded successful");
        LOG.info("Accept the Cookies");
        PageObjectUtil.waitUntilVisible(driver, atgHomePageObject.getAcceptAllCookiesButton());
        atgHomePageObject.getAcceptAllCookiesButton().click();
        assertTrue(atgHomePageObject.getHorseBettingLink().isDisplayed(),
                "After home page landing horse racing link should be visible");
        LOG.info("Click Horse Betting link");
        atgHomePageObject.getHorseBettingLink().click();

        assertTrue(atgHomePageObject.getHoseBettingCalendarLink().isDisplayed(), "Horse betting calendar link should be visible");
        atgHomePageObject.getHoseBettingCalendarLink().click();

        V4PageObject v4PageObject = new V4PageObject(driver);
        PageObjectUtil.waitUntilVisible(driver, v4PageObject.getCalendarNextDayButton());
        assertTrue(v4PageObject.getCalendarNextDayButton().isDisplayed(), "Calendar next day button should be visible");
        v4PageObject.getCalendarNextDayButton().click();
        assertTrue(v4PageObject.getGameTypeV4Button().isDisplayed(), "Button to select V4 should be visible");
        v4PageObject.getGameTypeV4Button().click();

        PageObjectUtil.waitUntilVisible(driver, v4PageObject.getMoreHorizontalLink());
        assertTrue(v4PageObject.getMoreHorizontalLink().isDisplayed(),
                "After selecting V4 betting, More icon should be visible");
        LOG.info("Going to click More icon for creating new coupon");
        v4PageObject.getMoreHorizontalLink().click();
        LOG.info("Going to create a new coupon");
        v4PageObject.getMoreLinkItemByValue("new-coupon").click();
        v4PageObject.getConfirmButton().click();

        for (int i = 1; i <= 4; i++) {
            if (i != 1) {
                WebElement raceMenuLeg = v4PageObject.getRaceMenuLegByValue(i - 1).orElseThrow();
                assertTrue(raceMenuLeg.isDisplayed(), "Race Menu Leg Number: " + i + " for V4 should be visible");
                LOG.info("Going to change the Race leg to number: {}", i);
                raceMenuLeg.click();
            }

            String v4RaceLegNoOfPlayers = PropertyManager.getInstance().getV4RaceLegPlayersByValue(i);
            int totalPlayersToSelects;
            if (v4RaceLegNoOfPlayers.equalsIgnoreCase("all")) {
                totalPlayersToSelects = v4PageObject.getTotalHorseCount();
            } else {
                totalPlayersToSelects = Integer.parseInt(v4RaceLegNoOfPlayers);
            }
            for (int j = 1; j <= totalPlayersToSelects; j++) {
                WebElement horseByNumber = v4PageObject.getHorseNumberByValue(String.valueOf(j)).orElseThrow();
                assertTrue(horseByNumber.isDisplayed(), "Horse number" + j + "should be visible for selection");
                LOG.info("Going to select the horse number: {}, in race leg: {}", j, i);
                horseByNumber.click();
            }
        }

        assertTrue(v4PageObject.getPlayGameButton().isDisplayed(), "Spela Button should be visible");
        LOG.info("Going to click Spela button");
        v4PageObject.getPlayGameButton().click();
    }

}
