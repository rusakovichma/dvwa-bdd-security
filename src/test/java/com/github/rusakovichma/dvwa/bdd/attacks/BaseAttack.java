package com.github.rusakovichma.dvwa.bdd.attacks;

import com.github.rusakovichma.dvwa.bdd.driver.DriverFactory;
import com.github.rusakovichma.dvwa.bdd.Driver;
import com.github.rusakovichma.dvwa.bdd.features.AuthFeature;
import com.github.rusakovichma.dvwa.bdd.features.BaseFeature;
import org.openqa.selenium.WebDriver;

public abstract class BaseAttack {

    protected WebDriver driver = DriverFactory.createDriver(Driver.Chrome);
    protected AuthFeature authFeature = new AuthFeature(driver);
    protected BaseFeature baseFeature = new BaseFeature(driver);

}
