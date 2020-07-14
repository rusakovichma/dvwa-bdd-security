package com.github.rusakovichma.dvwa.bdd.runner;

import com.github.rusakovichma.dvwa.bdd.selenium.driver.DriverFactory;

import static com.github.rusakovichma.dvwa.bdd.DvwaSettings.ZAP_ADDRESS;
import static com.github.rusakovichma.dvwa.bdd.DvwaSettings.ZAP_PORT;
import static com.github.rusakovichma.dvwa.bdd.selenium.driver.DriverFactory.PROXY_ADDRESS_SYSTEM_PROPERTY;
import static com.github.rusakovichma.dvwa.bdd.selenium.driver.DriverFactory.PROXY_PORT_SYSTEM_PROPERTY;

abstract class DvwaAttackRunner {

    DvwaAttackRunner() {
    }

    protected static void setUpProxy() {
        System.setProperty(PROXY_ADDRESS_SYSTEM_PROPERTY, ZAP_ADDRESS);
        System.setProperty(PROXY_PORT_SYSTEM_PROPERTY, String.valueOf(ZAP_PORT));
    }

    protected static void destroyDriver() {
        DriverFactory.closeAndDestroyDriver();
    }
}
