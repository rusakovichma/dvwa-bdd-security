package com.github.rusakovichma.dvwa.bdd.driver;

import com.github.rusakovichma.dvwa.bdd.selenium.Driver;
import com.github.rusakovichma.dvwa.bdd.selenium.DriverHelper;
import com.github.rusakovichma.dvwa.bdd.DvwaSettings;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverFactory {

    public static WebDriver webDriverInstance;

    private DriverFactory(){
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(getChromeZapProxyArgs());

        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8989");
        options.setCapability("proxy", proxy);

        return options;
    }

    private static List<String> getChromeZapProxyArgs() {
        List<String> chromeSwitches = new ArrayList<String>();
        chromeSwitches.add(String.format("--proxy-server=http://%s:%d",
                DvwaSettings.ZAP_ADDRESS, DvwaSettings.ZAP_PORT));
        chromeSwitches.add("--ignore-certificate-errors");
        return chromeSwitches;
    }

    public synchronized static WebDriver createDriver(Driver driver){
        if (webDriverInstance == null){
            DriverHelper.setupDriver(driver);
            switch (driver){
                case Gecko:
                    webDriverInstance = new FirefoxDriver();
                case Chrome:
                    webDriverInstance = new ChromeDriver(getChromeOptions());
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
        webDriverInstance = null;
    }

}
