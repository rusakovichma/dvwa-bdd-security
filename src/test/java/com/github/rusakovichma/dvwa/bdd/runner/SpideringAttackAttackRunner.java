package com.github.rusakovichma.dvwa.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/SpideringAttack.feature",
        glue = {"com.github.rusakovichma.dvwa.bdd.attacks"}
)
public class SpideringAttackAttackRunner extends DvwaAttackRunner {

    @BeforeClass
    public static void setup() {
        setUpProxy();
    }

}
