package com.github.rusakovichma.features;

import com.github.rusakovichma.DvwaSettings;
import org.apache.http.protocol.UriHttpRequestHandlerMapper;
import org.openqa.selenium.WebDriver;

public class BaseFeature {

    private WebDriver webDriver;

    public BaseFeature(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void goTo(String relativePath){
        webDriver.get(DvwaSettings.DVWA_BASE_URL + relativePath);
    }

}
