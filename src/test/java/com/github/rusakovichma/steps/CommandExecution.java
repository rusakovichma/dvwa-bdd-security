package com.github.rusakovichma.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class CommandExecution extends BaseStep {

    @Given("^user logins with '(.*)' and '(.*)'$")
    public void user_login(String username, String password) throws Throwable {
        authFeature.goHomeAndLogin(username, password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @And("^go to '(.*)' page$")
    public void go_to_vulnerabilities_exec_page(String path){
        baseFeature.goTo(path);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("^user enters '(.*)' and clicks '(.*)'$")
    public void user_enters_cat_etc_passwd_and_click_Submit(String command, String btnName) throws Throwable {
        driver.findElement(By.xpath("//div[@class='vulnerable_code_area']//input[@name='ip']")).sendKeys(command);
        driver.findElement(By.name(btnName)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^user sees the content of passwd file and root's '(.*)' pwd string$")
    public void user_sees_the_content_of_passwd_file_and_root_s_root_x_root_root_bin_bash_string(String rootPwdString) throws Throwable {
        String commendResponse = driver.findElement(By.xpath("//div[@class='vulnerable_code_area']//pre[1]")).getText();
        assertTrue(commendResponse.contains(rootPwdString));
    }
}
