package com.github.rusakovichma.dvwa.bdd.driver;

import com.github.rusakovichma.dvwa.bdd.selenium.Driver;
import com.github.rusakovichma.dvwa.bdd.selenium.DriverHelper;
import com.github.rusakovichma.dvwa.bdd.DvwaSettings;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.List;

import static com.github.rusakovichma.dvwa.bdd.DvwaSettings.ZAP_ADDRESS;
import static com.github.rusakovichma.dvwa.bdd.DvwaSettings.ZAP_PORT;

public class DriverFactory {

    public static WebDriver webDriverInstance;

    private DriverFactory(){
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(getChromeZapProxyArgs());

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(String.format("%s:%d", ZAP_ADDRESS, ZAP_PORT));
        options.setCapability("proxy", proxy);

        return options;
    }

    private static List<String> getChromeZapProxyArgs() {
        List<String> chromeSwitches = new ArrayList<String>();
        chromeSwitches.add(String.format("--proxy-server=%s:%d",
                ZAP_ADDRESS, ZAP_PORT));
        chromeSwitches.add("--ignore-certificate-errors");
        chromeSwitches.add("--proxy-bypass-list=<-loopback>");
        return chromeSwitches;
    }

    private static FirefoxOptions getFirefoxOptions(){
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("network.proxy.allow_hijacking_localhost", "true");
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", ZAP_ADDRESS);
        profile.setPreference("network.proxy.http_port", ZAP_PORT);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        return options;
    }

    public synchronized static WebDriver createDriver(Driver driver){
        if (webDriverInstance == null){
            DriverHelper.setupDriver(driver);
            switch (driver){
                case Gecko:
                    webDriverInstance = new FirefoxDriver(getFirefoxOptions());
                    break;
                case Chrome:
                    webDriverInstance = new ChromeDriver(getChromeOptions());
                    break;
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
