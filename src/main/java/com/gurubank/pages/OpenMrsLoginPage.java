package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenMrsLoginPage extends BasePage{
    public OpenMrsLoginPage(WebDriver driver) {
        super(driver);
    }

     public void loginToOpenMrs(String userName, String password) {
         try {
             driver.findElement(By.id("username")).sendKeys(userName);
             driver.findElement(By.id("password")).sendKeys(password);
             driver.findElement(By.cssSelector("[value='Log In']")).click();
             Thread.sleep(3000);
             driver.findElement(By.linkText("Log out")).click();
             Thread.sleep(2000);
         } catch (Exception e) {
             System.out.println("Exception occurred while Login to the application : " + e.getMessage());
         }
     }
}
