package com.github.rusakovichma.dvwa.bdd.attacks;

import com.github.rusakovichma.dvwa.bdd.driver.DriverFactory;
import com.github.rusakovichma.dvwa.bdd.selenium.Driver;
import org.openqa.selenium.WebDriver;

abstract class GeckoBasedAttack extends BaseAttack {

    @Override
    public WebDriver createDriver() {
        return DriverFactory.createDriver(Driver.Gecko);
    }
}
