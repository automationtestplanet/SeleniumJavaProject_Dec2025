package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAccountPage extends BasePage{
    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "accountno")
    private WebElement accountNumberElement;

    @FindBy(name = "AccSubmit")
    private WebElement submitButton;

    public void setAccountNumber(String accountId) {
        accountNumberElement.sendKeys(accountId);
    }

    public void clickSubmitButton() {
        try {
            submitButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking on Delete Account submit button : " + e.getMessage());
        }
    }

    public void deleteAccount(String accountId) {
        try {
            setAccountNumber(accountId);
            clickSubmitButton();
            acceptAlert();
            refreshPage();
        } catch (Exception e) {
            System.out.println("Exception occurred while deleting the account : " + e.getMessage());
        }
    }
}
