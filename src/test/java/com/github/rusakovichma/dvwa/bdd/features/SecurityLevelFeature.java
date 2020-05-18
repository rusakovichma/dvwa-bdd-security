package com.github.rusakovichma.dvwa.bdd.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SecurityLevelFeature extends AuthFeature {

    public static enum SecurityLevel{
        Low,
        Medium,
        High,
        Impossible
    }

    private final WebDriver webDriver;

    public SecurityLevelFeature(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void setSecurityLevel(SecurityLevel level){
        goTo("security.php");

        Select securityLevelSelect = new Select(webDriver.findElement(By.name("security")));
        securityLevelSelect.selectByVisibleText(level.name());

        webDriver.findElement(By.name("seclev_submit")).click();
    }


}
