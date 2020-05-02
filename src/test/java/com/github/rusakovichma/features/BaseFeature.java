package com.github.rusakovichma.features;

import org.apache.http.protocol.UriHttpRequestHandlerMapper;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class BaseFeature {

    private WebDriver webDriver;

    public BaseFeature(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
