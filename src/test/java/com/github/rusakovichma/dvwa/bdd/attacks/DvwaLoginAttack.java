package com.github.rusakovichma.dvwa.bdd.attacks;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Login test for DVWA.
 */
public class DvwaLoginAttack extends BaseAttack {

    @Given("^user is on DVMA homepage$")
    public void user_is_on_DVMA_homepage() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        authFeature.goHome();
    }

    @When("^user enters '(.*)' and '(.*)'$")
    public void user_enters_admin_and_password(String username, String password) throws Throwable {
        authFeature.login(username, password);
    }

    @Then("^the message '(.*)' is displayed$")
    public void success_message_is_displayed(String welcomeMessage) throws Throwable {
        String actual = driver.findElement(By.xpath("//div[@id='main_body']//h1")).getText();
        Assert.assertEquals(welcomeMessage, actual);
        authFeature.goLogout();
    }

    @Given("^user login the application with '(.*)' and '(.*)'$")
    public void user_login_the_application_with_admin_and_password(String username, String password) throws Throwable {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        authFeature.goHomeAndLogin(username, password);
    }

    @When("^user perform logout$")
    public void user_perform_logout() throws Throwable {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        authFeature.goLogout();
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
