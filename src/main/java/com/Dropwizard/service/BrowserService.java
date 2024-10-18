package com.Dropwizard.service;

public interface BrowserService {
    void start(String url);
    void stop();
    String getActiveTabUrl();
    void cleanup();
}