package com.github.rusakovichma.dvwa.bdd.attacks;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class SqlInjectionAttack extends ChromeBasedAttack {

    @When("^user enters \"(.*)\" and clicks '(.*)'$")
    public void user_enters_and_clicks_Submit(String payload, String btnName) throws Throwable {
        driver.findElement(By.xpath("//div[@class='vulnerable_code_area']//input[@name='id']"))
                .sendKeys(payload);
        driver.findElement(By.name(btnName)).click();
    }

    @Then("^user gains access to the following list of users$")
    public void user_gains_access_to_the_following_list_of_users(DataTable usersTable) throws Throwable {
        List<List<String>> usersData =  usersTable.asLists(String.class);
        for (int i = 0; i < usersData.size(); i++) {
            String exposedUserData = driver.findElement(By.xpath(
                    String.format("//div[@class='vulnerable_code_area']//pre[%d]", i+1)))
                    .getText();

            assertTrue(exposedUserData.contains(usersData.get(i).get(0)));
            assertTrue(exposedUserData.contains(usersData.get(i).get(1)));
        }
    }

}
