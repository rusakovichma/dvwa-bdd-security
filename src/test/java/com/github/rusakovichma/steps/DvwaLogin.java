package com.github.rusakovichma.steps;

import static org.junit.Assert.assertTrue;

import com.github.rusakovichma.driver.DriverFactory;
import com.github.rusakovichma.dvwa.bdd.Driver;
import com.github.rusakovichma.features.AuthFeature;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Login test for simple App.
 */
public class DvwaLogin
{
    private static WebDriver driver = DriverFactory.createDriver(Driver.Chrome);
    private AuthFeature authFeature = new AuthFeature(driver);

    @Given("^user is on DVMA homepage$")
    public void user_is_on_DVMA_homepage() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/login.php");
    }

    @When("^user enters admin and password$")
    public void user_enters_admin_and_password() throws Throwable {
        authFeature.login("admin", "password");
    }

    @Then("^Home page is displayed$")
    public void success_message_is_displayed() throws Throwable {
        String exp_message = "Welcome to Damn Vulnerable Web Application!";
        String actual = driver.findElement(By.xpath("//div[@id='main_body']//h1")).getText();
        Assert.assertEquals(exp_message, actual);
        DriverFactory.closeDriver();
    }
}
