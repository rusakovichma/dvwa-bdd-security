package com.github.rusakovichma.dvwa.bdd.selenium.driver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.UnexpectedAlertBehaviour.IGNORE;
import static org.openqa.selenium.remote.CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR;

public class DriverFactory {

    public static final String PROXY_ADDRESS_SYSTEM_PROPERTY = "selenium.proxy.address";
    public static final String PROXY_PORT_SYSTEM_PROPERTY = "selenium.proxy.port";

    public static WebDriver webDriverInstance;

    private DriverFactory() {
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        String proxyAddress = System.getProperty(PROXY_ADDRESS_SYSTEM_PROPERTY);
        String proxyPort = System.getProperty(PROXY_PORT_SYSTEM_PROPERTY);

        if (proxyAddress != null && proxyPort != null) {
            options.addArguments(getChromeProxyArgs(proxyAddress, proxyPort));

            Proxy proxy = new Proxy();
            proxy.setHttpProxy(String.format("%s:%s", proxyAddress, proxyPort));
            options.setCapability("proxy", proxy);
        }

        options.setCapability(UNEXPECTED_ALERT_BEHAVIOUR, IGNORE);

        return options;
    }

    private static List<String> getChromeProxyArgs(String proxyAddress, String proxyPort) {
        List<String> chromeSwitches = new ArrayList<String>();
        chromeSwitches.add(String.format("--proxy-server=%s:%s",
                proxyAddress, proxyPort));
        chromeSwitches.add("--ignore-certificate-errors");
        chromeSwitches.add("--proxy-bypass-list=<-loopback>");
        return chromeSwitches;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile profile = new FirefoxProfile();

        String proxyAddress = System.getProperty(PROXY_ADDRESS_SYSTEM_PROPERTY);
        String proxyPort = System.getProperty(PROXY_PORT_SYSTEM_PROPERTY);

        if (proxyAddress != null && proxyPort != null) {
            profile.setPreference("network.proxy.allow_hijacking_localhost", "true");
            profile.setPreference("network.proxy.type", 1);
            profile.setPreference("network.proxy.http", proxyAddress);
            profile.setPreference("network.proxy.http_port", proxyPort);
        }

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(UNEXPECTED_ALERT_BEHAVIOUR, IGNORE);
        options.setProfile(profile);

        return options;
    }

    public synchronized static WebDriver createDriver(Driver driver) {
        if (webDriverInstance == null) {
            DriverHelper.setupDriver(driver);
            switch (driver) {
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

    public synchronized static void closeDriver() {
        if (webDriverInstance != null) {
            webDriverInstance.quit();
        }
    }

    public synchronized static void closeAndDestroyDriver() {
        closeDriver();
        webDriverInstance = null;
    }

}
