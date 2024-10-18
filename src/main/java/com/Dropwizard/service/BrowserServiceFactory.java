package com.Dropwizard.service;

public class BrowserServiceFactory {
    private static ChromeService chromeService;
    private static FirefoxService firefoxService;

    public static BrowserService getBrowserService(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            if (chromeService == null) {
                chromeService = new ChromeService();
            }
            return chromeService;
        } else if (browser.equalsIgnoreCase("firefox")) {
            if (firefoxService == null) {
                firefoxService = new FirefoxService();
            }
            return firefoxService;
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}

