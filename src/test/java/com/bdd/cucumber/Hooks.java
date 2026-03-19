package com.bdd.cucumber;

import com.gurubank.commons.Commons;
import com.gurubank.commons.DriverManager;
import com.gurubank.pages.BasePage;
import com.gurubank.pages.HomePage;
import com.gurubank.pages.LoginPage;
import com.gurubank.utils.ReadConfigProperties;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;

public class Hooks {

    static BasePage basePage = new BasePage(DriverManager.getDriver());
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    HomePage homePage = new HomePage(DriverManager.getDriver());
    int counter = 1;

    @BeforeAll()
    public static void beforeAll() {
        basePage.launchApplication(ReadConfigProperties.getConfigPropertyValue("prod.url"));
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
    }

    @Before
    public void beforeScenario() {
        loginPage.loginToGuruBank(ReadConfigProperties.getConfigPropertyValue("prod.username"), ReadConfigProperties.getConfigPropertyValue("prod.password"));
        Assert.assertTrue(homePage.verifyManagerId(ReadConfigProperties.getConfigPropertyValue("prod.username")), "Manager ID is not correct");
    }

    @BeforeStep()
    public void beforeStep() {

    }

    @AfterStep()
    public void afterStep(Scenario scenario) {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            byte[] screenshotByteArr = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(screenshotByteArr, "image/png", "Screenshot"+counter++);

        }catch (Exception e){
            System.out.println("Exception occurred while capturing screenshot : " + e.getMessage());
        }
    }

    @After
    public void afterScenario() {
        homePage.logoutFromGuruBank();
        Commons.captureScreenShot();
    }

    @AfterAll()
    public static void afterAll() {
        basePage.closeApplication();
        DriverManager.setDriver(null);
    }

}
