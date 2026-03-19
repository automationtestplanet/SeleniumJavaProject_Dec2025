package com.gurubank.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    public static WebDriver driver;

     private DriverManager() {
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--headless");
         driver = new ChromeDriver(options);
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
