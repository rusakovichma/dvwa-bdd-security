package com.github.rusakovichma.dvwa.bdd.attacks;

import com.github.rusakovichma.dvwa.bdd.features.AuthFeature;
import com.github.rusakovichma.dvwa.bdd.features.BaseFeature;
import org.openqa.selenium.WebDriver;

abstract class BaseAttack {

    protected WebDriver driver = createDriver();
    protected AuthFeature authFeature = new AuthFeature(driver);
    protected BaseFeature baseFeature = new BaseFeature(driver);

    public abstract WebDriver createDriver();

}
