package com.github.rusakovichma.dvwa.bdd.runner;

import com.github.rusakovichma.dvwa.bdd.driver.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/SqlInjectionAttack.feature",
        glue= {"com.github.rusakovichma.dvwa.bdd.attacks"}
)
public class SqlInjectionAttackRunner {

    @AfterClass
    public static void teardown() {
        DriverFactory.closeAndDestroyDriver();
    }
}
