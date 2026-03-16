package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "uid")
    private WebElement userNameElement;

    @FindBy(name = "password")
    private WebElement passwordElement;

    @FindBy(name = "btnLogin")
    private WebElement loginButton;


    public void setUserName(String userName) {
        userNameElement.sendKeys(userName);
    }

    public void setPassword(String password) {
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginToGuruBank(String userName, String password) {
        try {
            setUserName(userName);
            setPassword(password);
            clickLoginButton();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Exception occurred while Login to the application : " + e.getMessage());
        }
    }

}
