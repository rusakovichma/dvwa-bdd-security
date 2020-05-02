package com.github.rusakovichma.runner;

import com.github.rusakovichma.driver.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/CommandExecution.feature",
        glue= {"com.github.rusakovichma.steps"}
)
public class CommandExecutionRunner {

    @AfterClass
    public static void teardown() {
        DriverFactory.closeAndDestroyDriver();
    }

}
