package com.github.rusakovichma.dvwa.bdd.functional;

import com.github.rusakovichma.dvwa.bdd.features.AuthFeature;
import com.github.rusakovichma.dvwa.bdd.features.BaseFeature;
import com.github.rusakovichma.dvwa.bdd.selenium.SeleniumFacade;
import org.openqa.selenium.WebDriver;

public abstract class DvwaFunctional extends SeleniumFacade {

    protected final WebDriver driver = createDriver();

    protected final AuthFeature authFeature = new AuthFeature(driver);
    protected final BaseFeature baseFeature = new BaseFeature(driver);

    protected abstract WebDriver createDriver();

    @Override
    protected WebDriver getDriver() {
        return driver;
    }

}
