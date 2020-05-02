package com.github.rusakovichma.steps;

import com.github.rusakovichma.DvwaSettings;
import com.github.rusakovichma.driver.DriverFactory;
import com.github.rusakovichma.dvwa.bdd.Driver;
import com.github.rusakovichma.features.AuthFeature;
import com.github.rusakovichma.features.BaseFeature;
import org.openqa.selenium.WebDriver;

public abstract class BaseStep {

    protected WebDriver driver = DriverFactory.createDriver(Driver.Chrome);
    protected AuthFeature authFeature = new AuthFeature(driver);
    protected BaseFeature baseFeature = new BaseFeature(driver);

}
