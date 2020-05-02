package com.github.rusakovichma.dvwa.bdd.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthFeature extends BaseFeature{

    private final WebDriver webDriver;

    public AuthFeature(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void goHome(){
        goTo("login.php");
    }

    public void login(String username, String password){
        webDriver.findElement(By.name("username")).sendKeys(username);
        webDriver.findElement(By.name("password")).sendKeys(password);
        webDriver.findElement(By.name("Login")).click();
    }

    public void goHomeAndLogin(String username, String password){
        goHome();
        login(username, password);
    }

    public String getLoginMessage(){
        return webDriver.findElement(By.className("message")).getText();
    }

    public void goLogout(){
        goTo("logout.php");
    }
}
