package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewAccountPage extends BasePage {
    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "cusid")
    private WebElement customerIdElement;

    @FindBy(name = "selaccount")
    private WebElement accountTypeElement;

    @FindBy(name = "inideposit")
    private WebElement initialDepositElement;

    @FindBy(name = "button2")
    private WebElement submitButton;

    public void setCustomerId(String customerId) {
        customerIdElement.sendKeys(customerId);
    }

    public void selectAccountType(String accountType) {
        selectDropDownByVisibleText(accountTypeElement, accountType);
    }

    public void setInitialDeposit(String initialDeposit) {
        initialDepositElement.sendKeys(initialDeposit);
    }

    public void clickSubmitButton() {
        try {
            submitButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking on New Account submit button : " + e.getMessage());
        }
    }


    public void createNewAccount(String customerId, String accountType, String initialDeposit) {
        try {
            setCustomerId(customerId);
            selectAccountType(accountType);
            setInitialDeposit(initialDeposit);
            clickSubmitButton();
        } catch (Exception e) {
            System.out.println("Exception occurred while creating new account : " + e.getMessage());
        }
    }

    public String getAccountID() {
        try {
            return getTextFromElement("Account ID");
        } catch (Exception e) {
            System.out.println("Exception occurred while getting Account ID : " + e.getMessage());
            return null;
        }
    }

    public String getCustomerID() {
        try {
            return getTextFromElement("Customer ID");
        } catch (Exception e) {
            System.out.println("Exception occurred while getting Customer ID : " + e.getMessage());
            return null;
        }
    }

    public String getAccountType() {
        try {
            return getTextFromElement("Account Type");
        } catch (Exception e) {
            System.out.println("Exception occurred while getting Account Type : " + e.getMessage());
            return null;
        }
    }

    public String getCurrentAmount() {
        try {
            return getTextFromElement("Current Amount");
        } catch (Exception e) {
            System.out.println("Exception occurred while getting Current Amount : " + e.getMessage());
            return null;
        }
    }

    public boolean verifyNewAccountDetails(String expCustomerId, String expAccountType, String expInitialDeposit) {
        return getCustomerID().equals(expCustomerId) && getAccountType().equals(expAccountType) && getCurrentAmount().equals(expInitialDeposit);
    }
}
