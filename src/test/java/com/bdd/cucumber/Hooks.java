package com.bdd.cucumber;

import com.gurubank.commons.DriverManager;
import com.gurubank.pages.BasePage;
import com.gurubank.pages.HomePage;
import com.gurubank.pages.LoginPage;
import com.gurubank.utils.ReadConfigProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Hooks {

    BasePage basePage = new BasePage(DriverManager.getDriver());
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    HomePage homePage = new HomePage(DriverManager.getDriver());

    @Before
    public void beforeScenario() {
        basePage.launchApplication(ReadConfigProperties.getConfigPropertyValue("prod.url"));
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
        loginPage.loginToGuruBank(ReadConfigProperties.getConfigPropertyValue("prod.username"), ReadConfigProperties.getConfigPropertyValue("prod.password"));
        Assert.assertTrue(homePage.verifyManagerId(ReadConfigProperties.getConfigPropertyValue("prod.username")), "Manager ID is not correct");
    }

    @After
    public void afterScenario() {
        homePage.logoutFromGuruBank();
        basePage.closeApplication();
    }


}
