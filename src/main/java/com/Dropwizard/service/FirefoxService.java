package com.Dropwizard.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxService implements BrowserService {
    private WebDriver driver;

    public FirefoxService() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public void start(String url) {
        WebDriverManager.firefoxdriver().setup();
        if (driver == null) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new FirefoxDriver(options);
        }
        // Ensure the URL is properly formatted
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    @Override
    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public String getActiveTabUrl() {
        if (driver != null) {
            return driver.getCurrentUrl();
        }
        return null;
    }

    @Override
    public void cleanup() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }
}

