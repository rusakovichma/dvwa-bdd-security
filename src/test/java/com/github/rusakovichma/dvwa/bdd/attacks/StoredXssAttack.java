package com.github.rusakovichma.dvwa.bdd.attacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;

import static junit.framework.TestCase.assertTrue;

public class StoredXssAttack extends DvwaChromeBasedAttack {

    @When("^user enter name '(.*)' and message '(.*)' and clicks '(.*)'$")
    public void user_enter_name_John_Doe_and_message_script_alert_document_cookie_script_and_clicks_Sign_Guestbook(
            String name, String message, String btnName) throws Throwable {

        elementByXpath("//div[@class='vulnerable_code_area']//input[@name='txtName']")
                .sendKeys(name);

        elementByXpath("//div[@class='vulnerable_code_area']//textarea[@name='mtxMessage']")
                .sendKeys(message);

        elementByXpath(String.format("//input[@value='%s']", btnName))
                .click();
    }

    @Then("^user refresh the page and see the alert with '(.*)' cookie$")
    public void user_refresh_the_page_and_see_the_alert_with_PHPSESSID_cookie(
            String cookieName) throws Throwable {
        try {
            refresh();
        } catch (UnhandledAlertException f) {
            final Alert cookieAlert = alert();
            assertTrue(cookieAlert.getText().contains(cookieName));
            cookieAlert.accept();
        }
    }

}
