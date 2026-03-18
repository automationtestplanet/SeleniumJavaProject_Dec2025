package com.gurubank.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    public static WebDriver driver;

     private DriverManager() {
         driver = new ChromeDriver();
     }

     public static WebDriver getDriver() {
         if (driver == null) {
             new DriverManager();
         }
         return driver;
     }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver = driver;
    }

}
