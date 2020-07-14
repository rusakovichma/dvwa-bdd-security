package com.github.rusakovichma.dvwa.bdd.attacks;

import com.github.rusakovichma.dvwa.bdd.functional.DvwaFunctional;
import com.github.rusakovichma.dvwa.bdd.selenium.driver.DriverFactory;
import com.github.rusakovichma.dvwa.bdd.selenium.driver.Driver;
import org.openqa.selenium.WebDriver;

abstract class DvwaChromeBasedAttack extends DvwaFunctional {

    @Override
    public WebDriver createDriver() {
        return DriverFactory.createDriver(Driver.Chrome);
    }
}
