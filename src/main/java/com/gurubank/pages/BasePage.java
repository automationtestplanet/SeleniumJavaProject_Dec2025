package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchApplication(String url) throws Exception {
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    public boolean verifyPageTitle(String expectedPageTitle) {
        return driver.getTitle().contains(expectedPageTitle);
    }

    public boolean verifyPageHeading(String headingName) {
        return driver.findElement(By.xpath("//p[contains(text(),'" + headingName + "')]")).isDisplayed();
    }

    public void clickBankerOption(String optionName) throws InterruptedException {
        driver.findElement(By.linkText(optionName)).click();
        Thread.sleep(2000);
    }

    public void acceptAlert() throws InterruptedException {
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }

    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    public void clickUsingJavaScriptExecutor(WebElement element) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
    }

    public void closeApplication() {
        driver.close();
    }

}
