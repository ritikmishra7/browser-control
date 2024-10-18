package com.Dropwizard.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeService implements BrowserService {
    private WebDriver driver;

    public ChromeService() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public void start(String url) {

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
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
            driver = null;
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

