package org.atg.assignment.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static final Logger LOG = LogManager.getLogger(PropertyManager.class);

    private static PropertyManager instance;
    private String applicationURL;
    private String browser;
    private Long explicitWaitTime;
    private String v4RaceLeg1Players;
    private String v4RaceLeg2Players;
    private String v4RaceLeg3Players;
    private String v4RaceLeg4Players;

    private String petStoreApplicationURL;
    private String petAPIPath;
    private String findPetsByStatusAPIPath;

    private String cronExpression;

    public static PropertyManager getInstance () throws IOException {
        if (instance == null) {
            instance = new PropertyManager();
            instance.loadData();
        }
        return instance;
    }

    private void loadData() throws IOException {
        Properties prop = new Properties();
        try {
            LOG.info("Loading property file ");
            prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));

            applicationURL = prop.getProperty("applicationURL", "https://www.atg.se");
            browser = prop.getProperty("browser", "FIREFOX");
            explicitWaitTime = Long.parseLong(prop.getProperty("explicitWaitTime", "10"));

            v4RaceLeg1Players = prop.getProperty("v4RaceLeg1Players", "1");
            v4RaceLeg2Players = prop.getProperty("v4RaceLeg2Players", "1");
            v4RaceLeg3Players = prop.getProperty("v4RaceLeg3Players", "1");
            v4RaceLeg4Players = prop.getProperty("v4RaceLeg4Players", "1");

            petStoreApplicationURL = prop.getProperty("petStoreApplicationURL", "https://petstore.swagger.io");
            petAPIPath = prop.getProperty("petAPIPath", "/v2/pet");
            findPetsByStatusAPIPath = prop.getProperty("findPetsByStatusAPIPath", "/findByStatus?status=");

            cronExpression = prop.getProperty("cronExpression", "0 0 6 */1 * ?");

        } catch (IOException e) {
            LOG.error("Configuration properties file cannot be found", e);
            throw e;
        }
    }

    public String getApplicationURL() {
        return applicationURL;
    }

    public String getBrowser() {
        return browser;
    }

    public Long getExplicitWaitTime() {
        return explicitWaitTime;
    }

    public String getV4RaceLegPlayersByValue(int legValue) {
        return switch (legValue) {
            case 2 -> v4RaceLeg2Players;
            case 3 -> v4RaceLeg3Players;
            case 4 -> v4RaceLeg4Players;
            default -> v4RaceLeg1Players;
        };
    }

    public String getPetStoreApplicationURL() {
        return petStoreApplicationURL;
    }

    public String getPetAPIPath() {
        return petAPIPath;
    }

    public String getFindPetsByStatusAPIPath() {
        return findPetsByStatusAPIPath;
    }

    public String getCronExpression() {
        return cronExpression;
    }
}