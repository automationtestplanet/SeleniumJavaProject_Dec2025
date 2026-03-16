package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyManagerId(String expManagerId) {
        try {
            return driver.findElement(By.xpath("//td[contains(text(),'Manger Id :')]")).getText().contains(expManagerId);
        } catch (Exception e) {
            System.out.println("Exception occurred while verifying Manager ID : " + e.getMessage());
            return false;
        }
    }

    public void logoutFromGuruBank() {
        try {
            WebElement logOut = driver.findElement(By.linkText("Log out"));
            clickUsingJavaScriptExecutor(logOut);
            acceptAlert();
        } catch (Exception e) {
            System.out.println("Exception occurred while Logout from the application : " + e.getMessage());
        }
    }
}
