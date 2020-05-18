package com.github.rusakovichma.dvwa.bdd.driver;

import com.github.rusakovichma.dvwa.bdd.Driver;
import com.github.rusakovichma.dvwa.bdd.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver webDriverInstance;

    private DriverFactory(){
    }

    public synchronized static WebDriver createDriver(Driver driver){
        if (webDriverInstance == null){
            DriverHelper.setupDriver(driver);
            switch (driver){
                case Gecko:
                    webDriverInstance = new FirefoxDriver();
                case Chrome:
                    webDriverInstance = new ChromeDriver();
            }
        }
        return webDriverInstance;
    }

    public synchronized static void closeDriver(){
        if (webDriverInstance != null){
            webDriverInstance.quit();
        }
    }

    public synchronized static void closeAndDestroyDriver(){
        closeDriver();
        webDriverInstance.close();
        webDriverInstance = null;
    }

}
