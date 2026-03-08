package com.gurubank.demo;

import com.gurubank.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class GuruBankTestWithPageObjects {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        NewCustomerPage newCustomerPage = new NewCustomerPage(driver);
        DeleteCustomerPage deleteCustomerPage = new DeleteCustomerPage(driver);

        basePage.launchApplication("https://demo.guru99.com/V4/index.php");
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
        loginPage.loginToGuruBank("mngr655463", "bUtAvys");
        Assert.assertTrue(homePage.verifyManagerId("mngr655463"), "Manager ID is not correct");
        homePage.clickBankerOption("New Customer");
        Assert.assertTrue(homePage.verifyPageHeading("New Customer"), "New Customer page is not displayed");
        newCustomerPage.createNewCustomer("Test Customer", "Male", "2020-03-06", "HNO 25 S R Nagar", "Hyderabad", "Telangana", "500038", "9876543210", "customer689@gmail.com", "TestCustomer@123");
        Assert.assertTrue(newCustomerPage.verifyPageHeading("Customer Registered Successfully!!!"), "Customer Registration was not successful");
        Assert.assertTrue(newCustomerPage.verifyNewCustomerDetails("Test Customer", "9876543210"), "Customer details are not correct");
        String customerId = newCustomerPage.getCustomerId();
        Assert.assertNotNull(customerId, "Customer ID is null");
        homePage.clickBankerOption("Delete Customer");
        Assert.assertTrue(homePage.verifyPageHeading("Delete Customer Form"), "Delete Customer page is not displayed");
        deleteCustomerPage.deleteCustomer(customerId);
        homePage.logoutFromGuruBank();
        basePage.closeApplication();
    }
}
