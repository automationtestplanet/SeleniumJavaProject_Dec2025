package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchApplication(String url) {
        try {
            driver.get(url);
            driver.manage().window().maximize();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Exception occurred while launching the application : " + e.getMessage());

        }
    }

    public boolean verifyPageTitle(String expectedPageTitle) {
        return driver.getTitle().contains(expectedPageTitle);
    }

    public boolean verifyPageHeading(String headingName) {
        return driver.findElement(By.xpath("//p[contains(text(),'" + headingName + "')]")).isDisplayed();
    }

    public void clickBankerOption(String optionName) {
        try {
            driver.findElement(By.linkText(optionName)).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking Banker : " + e.getMessage());

        }
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while accepting the Alert : " + e.getMessage());

        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while refresh the Page : " + e.getMessage());

        }
    }

    public void clickUsingJavaScriptExecutor(WebElement element) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", element);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while Clicking using JavaScriptExecutor : " + e.getMessage());

        }
    }

    public void closeApplication() {
        driver.close();
    }

    public void selectDropDownByVisibleText(WebElement dropDownElement, String visibleText) {
        try {
            Select select = new Select(dropDownElement);
            select.selectByVisibleText(visibleText);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while selecting dropdown value : " + e.getMessage());
        }
    }

    public void selectDropDownByIndex(WebElement dropDownElement, int index) {
        try {
            Select select = new Select(dropDownElement);
            select.selectByIndex(index);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while selecting dropdown value : " + e.getMessage());
        }
    }

    public void selectDropDownByValue(WebElement dropDownElement, String dropdownValue) {
        try {
            Select select = new Select(dropDownElement);
            select.selectByValue(dropdownValue);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while selecting dropdown value : " + e.getMessage());

        }
    }

    public String getTextFromElement(String filedName) {
        try {
            return driver.findElement(By.xpath("//td[text()='"+filedName+"']//following-sibling::td")).getText().trim();
        } catch (Exception e) {
            System.out.println("Exception occurred while getting text from element : " + e.getMessage());
            return null;
        }
    }

}
