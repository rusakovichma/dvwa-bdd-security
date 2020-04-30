package com.github.rusakovichma.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthFeature {

    private final WebDriver webDriver;

    public AuthFeature(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(String username, String password){
        webDriver.findElement(By.name("username")).sendKeys(username);
        webDriver.findElement(By.name("password")).sendKeys(password);
        webDriver.findElement(By.name("Login")).click();
    }

    public String getLoginMessage(){
        return webDriver.findElement(By.className("message")).getText();
    }

    public void logout(){
        webDriver.navigate().to("/logout.php");
    }
}
