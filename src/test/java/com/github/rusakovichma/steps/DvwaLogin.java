package com.github.rusakovichma.steps;

import static org.junit.Assert.assertTrue;

import com.github.rusakovichma.driver.DriverFactory;
import com.github.rusakovichma.dvwa.bdd.Driver;
import com.github.rusakovichma.features.AuthFeature;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.And;
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

    @When("^user enters '(.*)' and '(.*)'$")
    public void user_enters_admin_and_password(String username, String password) throws Throwable {
        authFeature.login(username, password);
    }

    @Then("^the message '(.*)' is displayed$")
    public void success_message_is_displayed(String welcomeMessage) throws Throwable {
        String actual = driver.findElement(By.xpath("//div[@id='main_body']//h1")).getText();
        Assert.assertEquals(welcomeMessage, actual);
        authFeature.logout();
    }

    @Given("^user login the application with '(.*)' and '(.*)'$")
    public void user_login_the_application_with_admin_and_password(String username, String password) throws Throwable {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://localhost/login.php");
        authFeature.login(username, password);
    }

    @When("^user perform logout$")
    public void user_perform_logout() throws Throwable {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        authFeature.logout();
    }

    @Then("^the users returns to the Home page$")
    public void the_users_returns_to_the_Home_page() throws Throwable {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertTrue(driver.getCurrentUrl().endsWith("login.php"));
    }

    @And("^he sees the '(.*)' message$")
    public void he_sees_the_You_have_logged_out_message(String logoutMessage) throws Throwable {
        String actualMessage = driver.findElement(By.xpath("//div[@id='content']//div[@class='message']")).getText();
        Assert.assertEquals(logoutMessage, actualMessage);
    }
}
