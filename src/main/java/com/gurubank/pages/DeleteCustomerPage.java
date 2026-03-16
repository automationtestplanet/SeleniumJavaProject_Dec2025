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
        try {
            driver.findElement(By.name("AccSubmit")).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking on Delete Customer submit button : " + e.getMessage());
        }
    }

    public void deleteCustomer(String customerId) {
        setCustomerId(customerId);
        clickDeleteCustomerSubmitButton();
        acceptAlert();
        refreshPage();
        acceptAlert();
    }
}
