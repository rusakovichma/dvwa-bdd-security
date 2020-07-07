package com.github.rusakovichma.dvwa.bdd.attacks;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.util.List;
import java.util.regex.Pattern;

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
        List<List<String>> usersData = usersTable.asLists(String.class);
        for (int i = 0; i < usersData.size(); i++) {
            String exposedUserData = driver.findElement(By.xpath(
                    String.format("//div[@class='vulnerable_code_area']//pre[%d]", i + 1)))
                    .getText();

            assertTrue(exposedUserData.contains(usersData.get(i).get(0)));
            assertTrue(exposedUserData.contains(usersData.get(i).get(1)));
        }
    }

    @Then("^user see (\\d+) users and database '(.*)' version$")
    public void user_see_users_and_database_MariaDB_deb_u_version(int usersNumber, String databaseVersion) throws Throwable {
        String exposedData = driver.findElement(By.xpath(
                String.format("//div[@class='vulnerable_code_area']//pre[%d]", usersNumber + 1)))
                .getText();

        assertTrue(exposedData.contains(databaseVersion));
    }

    @Then("^user see (\\d+) users and database user '(.*)'$")
    public void user_see_users_and_database_user(int usersNumber, String databaseUser) throws Throwable {
        String exposedData = driver.findElement(By.xpath(
                String.format("//div[@class='vulnerable_code_area']//pre[%d]", usersNumber + 1)))
                .getText();

        assertTrue(exposedData.contains(databaseUser));
    }

    @Then("^user see (\\d+) users and database name '(.*)'$")
    public void user_see_users_and_database_name(int usersNumber, String databaseName) throws Throwable {
        String exposedData = driver.findElement(By.xpath(
                String.format("//div[@class='vulnerable_code_area']//pre[%d]", usersNumber + 1)))
                .getText();

        assertTrue(exposedData.contains(databaseName));
    }

    @Then("^user see the tables in information_schema$")
    public void user_gains_access_to_the_tables_in_information_schema(DataTable infoSchemaTables) throws Throwable {
        List<String> tablesData = infoSchemaTables.asList(String.class);
        for (int i = 0; i < tablesData.size(); i++) {
            String exposedUserData = driver.findElement(By.xpath(
                    String.format("//div[@class='vulnerable_code_area']//pre[%d]", i + 1)))
                    .getText();

            assertTrue(exposedUserData.contains(tablesData.get(i)));
        }
    }

    @Then("^user see the '(.*)' user data and password md5 hash$")
    public void user_see_admin_password_hash(String userName) throws Throwable {
        String exposedData = driver.findElement(By.xpath(
                "//div[@class='vulnerable_code_area']//pre[1]"))
                .getText();

        assertTrue(exposedData.contains(userName));

        Pattern md5Pattern = Pattern.compile("[a-f0-9]{32}");
        assertTrue(md5Pattern.matcher(exposedData).find());
    }
}
