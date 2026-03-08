package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean  verifyManagerId(String expManagerId) throws InterruptedException {
        return driver.findElement(By.xpath("//td[contains(text(),'Manger Id :')]")).getText().contains(expManagerId);
    }

    public void logoutFromGuruBank() throws InterruptedException {
        WebElement logOut = driver.findElement(By.linkText("Log out"));
        clickUsingJavaScriptExecutor(logOut);
        acceptAlert();
    }
}
