package com.gurubank.demo;

import io.cucumber.prettyformatter.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WelcomePageTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("file:///C:/Users/Hello/Desktop/Welcome.html");
        Thread.sleep(5000);
        String title = driver.getTitle();
        System.out.println("Title is: " + title);

        String currURL = driver.getCurrentUrl();
        System.out.println("Current URL is: " + currURL);

        WebElement userNameField = driver.findElement(By.id("userName"));
        System.out.println("Place Holder: "+userNameField.getAttribute("placeholder"));
        System.out.println("Enabled? : "+userNameField.isEnabled());
        System.out.println("Displayed? : "+userNameField.isDisplayed());
        userNameField.sendKeys("mngr650380");

        WebElement passwordField = driver.findElement(By.name("Password"));
        System.out.println("Place Holder: "+passwordField.getAttribute("placeholder"));
        passwordField.sendKeys("gAqysAh");

        WebElement genderMaleRadioButton = driver.findElement(By.id("male"));
        System.out.println("Selected?: "+genderMaleRadioButton.isSelected());
        if(! genderMaleRadioButton.isSelected()){
            genderMaleRadioButton.click();
        }

        WebElement termsCheckBox = driver.findElement(By.className("checkbox"));
        System.out.println("Selected?: "+termsCheckBox.isSelected());
        if(! termsCheckBox.isSelected()){
            termsCheckBox.click();
        }

        WebElement textArea = driver.findElement(By.tagName("textarea"));
        textArea.sendKeys("Hello this is Selenium WebDriver class");
        Thread.sleep(5000);
        System.out.println("Text in the Text Area: "+textArea.getAttribute("value"));



        driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();
        Thread.sleep(3000);

        WebElement link = driver.findElement(By.partialLinkText("Seperate Window"));
        System.out.println("Link Full Name: "+link.getText());
        link.click();

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[value=\"Register\"]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/div[4]/input[2]")).sendKeys("Hello");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[4]/input[2]")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"div7\"]/input[2]")).sendKeys("Java");
        Thread.sleep(2000);




//        driver.quit();
    }
}
