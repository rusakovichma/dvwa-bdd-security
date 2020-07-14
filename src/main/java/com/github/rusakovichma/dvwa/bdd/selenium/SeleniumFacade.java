package com.github.rusakovichma.dvwa.bdd.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class SeleniumFacade {

    protected abstract WebDriver getDriver();

    protected WebElement element(By by) {
        return getDriver().findElement(by);
    }

    protected WebElement elementByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    protected WebElement elementByName(String name) {
        return getDriver().findElement(By.name(name));
    }

    protected void refresh() {
        getDriver().navigate().refresh();
    }

    protected Alert alert() {
        return getDriver().switchTo().alert();
    }

    protected WebDriver.Timeouts timeouts() {
        return getDriver().manage().timeouts();
    }

    protected String currentUrl() {
        return getDriver().getCurrentUrl();
    }

}
