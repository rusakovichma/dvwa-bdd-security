package com.github.rusakovichma.dvwa.bdd.features;

import com.github.rusakovichma.dvwa.bdd.DvwaSettings;
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
