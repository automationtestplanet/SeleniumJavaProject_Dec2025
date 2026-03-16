package com.gurubank;

import com.gurubank.pages.*;
import com.gurubank.utils.ReadTestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GuruBankTest {
    @Test(priority = 0)
    public void createNewCustomer() {
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);

        basePage.launchApplication("https://demo.guru99.com/V4/index.php");
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
        loginPage.loginToGuruBank("mngr655463", "bUtAvys");
        Assert.assertTrue(homePage.verifyManagerId("mngr655463"), "Manager ID is not correct");
        homePage.clickBankerOption("New Customer");
        Assert.assertTrue(homePage.verifyPageHeading("New Customer"), "New Customer page is not displayed");
        newCustomerPage.createNewCustomer("Automation Test Customer", "Male", "2020-03-06", "HNO 25 S R Nagar", "Hyderabad", "Telangana", "500038", "9987654321", "customer456@gmail.com", "TestCustomer@123");
        Assert.assertTrue(newCustomerPage.verifyPageHeading("Customer Registered Successfully!!!"), "Customer Registration was not successful");
        Assert.assertTrue(newCustomerPage.verifyNewCustomerDetails("Automation Test Customer", "9987654321"), "Customer details are not correct");
        Assert.assertNotNull(newCustomerPage.getCustomerId(), "Customer ID is null");
        ReadTestProperties.setTestPropertyValue("customer.id", newCustomerPage.getCustomerId());
        homePage.logoutFromGuruBank();
        basePage.closeApplication();
    }

    @Test(priority = 1, dependsOnMethods = "createNewCustomer", enabled = false)
    public void createNewAccount() {
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        NewAccountPage newAccountPage = new NewAccountPage(driver);
        basePage.launchApplication("https://demo.guru99.com/V4/index.php");
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
        loginPage.loginToGuruBank("mngr655463", "bUtAvys");
        Assert.assertTrue(homePage.verifyManagerId("mngr655463"), "Manager ID is not correct");
        homePage.clickBankerOption("New Account");
        Assert.assertTrue(homePage.verifyPageHeading("Add new account form"), "New Account page is not displayed");
        newAccountPage.createNewAccount(ReadTestProperties.getTestPropertyValue("customer.id"), "Savings", "5000");
        Assert.assertTrue(newAccountPage.verifyNewAccountDetails(ReadTestProperties.getTestPropertyValue("customer.id"), "Savings", "5000"), "New Account was not created successfully");
        Assert.assertNotNull(newAccountPage.getAccountID(), "Customer ID is null");
        ReadTestProperties.setTestPropertyValue("account.id", newAccountPage.getAccountID());
        homePage.logoutFromGuruBank();
        basePage.closeApplication();
    }

    @Test(priority = 2, dependsOnMethods = "createNewAccount",enabled = false)
    public void deleteAccount() {
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);

        basePage.launchApplication("https://demo.guru99.com/V4/index.php");
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
        loginPage.loginToGuruBank("mngr655463", "bUtAvys");
        Assert.assertTrue(homePage.verifyManagerId("mngr655463"), "Manager ID is not correct");
        homePage.clickBankerOption("Delete Account");
        Assert.assertTrue(homePage.verifyPageHeading("Delete Account Form"), "Delete Account page is not displayed");
        deleteAccountPage.deleteAccount(ReadTestProperties.getTestPropertyValue("account.id"));
        basePage.closeApplication();
    }

    @Test(priority = 3, dependsOnMethods = "createNewCustomer")
    public void deleteCustomer() {
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        DeleteCustomerPage deleteCustomerPage = new DeleteCustomerPage(driver);

        basePage.launchApplication("https://demo.guru99.com/V4/index.php");
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
        loginPage.loginToGuruBank("mngr655463", "bUtAvys");
        Assert.assertTrue(homePage.verifyManagerId("mngr655463"), "Manager ID is not correct");
        homePage.clickBankerOption("Delete Customer");
        Assert.assertTrue(homePage.verifyPageHeading("Delete Customer Form"), "Delete Customer page is not displayed");
        deleteCustomerPage.deleteCustomer(ReadTestProperties.getTestPropertyValue("customer.id"));
        homePage.logoutFromGuruBank();
        basePage.closeApplication();
    }


}
