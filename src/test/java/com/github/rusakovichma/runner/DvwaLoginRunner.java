package com.github.rusakovichma.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/DvwaLogin.feature",
        glue= {"com.github.rusakovichma.steps"}
)
public class DvwaLoginRunner {
}
