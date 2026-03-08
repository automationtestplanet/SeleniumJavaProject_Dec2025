package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteCustomerPage extends BasePage {

    public DeleteCustomerPage(WebDriver driver) {
        super(driver);
    }

    public void setCustomerId(String customerId) {
        driver.findElement(By.name("cusid")).sendKeys(customerId);
    }

    public void clickDeleteCustomerSubmitButton() {
        driver.findElement(By.name("AccSubmit")).click();
    }

    public void deleteCustomer(String customerId) throws InterruptedException {
        setCustomerId(customerId);
        clickDeleteCustomerSubmitButton();
        Thread.sleep(2000);
        acceptAlert();
        refreshPage();
        acceptAlert();
    }
}
