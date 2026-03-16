package com.gurubank;

import com.gurubank.pages.*;
import com.gurubank.utils.ExcelUtils;
import com.gurubank.utils.ReadConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseTest {

    WebDriver driver;
    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;
    NewCustomerPage newCustomerPage;
    DeleteCustomerPage deleteCustomerPage;

    // For tracking row index in data-driven tests
    protected AtomicInteger currentRowIndex = new AtomicInteger(1);
    protected ExcelUtils excelUtils = new ExcelUtils();
    protected String excelFilePath;
    protected String sheetName;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuit() {
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        newCustomerPage = new NewCustomerPage(driver);
        deleteCustomerPage = new DeleteCustomerPage(driver);

        // Initialize Excel file path and sheet name for data-driven tests
        excelFilePath = System.getProperty("user.dir") + ReadConfigProperties.getConfigPropertyValue("test.data.path");
        sheetName = ReadConfigProperties.getConfigPropertyValue("test.data.sheet.name");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        basePage.launchApplication(ReadConfigProperties.getConfigPropertyValue("prod.url"));
        Assert.assertTrue(basePage.verifyPageTitle("Guru99 Bank Home Page"), "Guru99 Bank Login Page is not displayed");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        loginPage.loginToGuruBank(ReadConfigProperties.getConfigPropertyValue("prod.username"), ReadConfigProperties.getConfigPropertyValue("prod.password"));
        Assert.assertTrue(homePage.verifyManagerId(ReadConfigProperties.getConfigPropertyValue("prod.username")), "Manager ID is not correct");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        homePage.logoutFromGuruBank();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        basePage.closeApplication();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuit() {
        driver = null;
        basePage = null;
        loginPage = null;
        homePage = null;
        newCustomerPage = null;
    }


    @DataProvider(name="DataDrivenTestData")
    public Iterator<Map<String, String>> getDataFromExcel() {
        ExcelUtils excelUtils = new ExcelUtils();
        List<Map<String, String>> dataList = excelUtils.readDataFromExcel(System.getProperty("user.dir") + ReadConfigProperties.getConfigPropertyValue("test.data.path"), ReadConfigProperties.getConfigPropertyValue("test.data.sheet.name"));
        return dataList.iterator();
    }

    /**
     * Updates the CustomerId in Excel for the current row
     * Call this method after a new customer is created in your test
     * @param customerId The newly generated customer ID
     */
    protected void updateCustomerIdInExcel(String customerId) {
        int rowIndex = currentRowIndex.getAndIncrement();
        excelUtils.updateCustomerId(excelFilePath, sheetName, rowIndex, customerId);
        System.out.println("✓ Updated CustomerId '" + customerId + "' in Excel row " + rowIndex);
    }

    /**
     * Get the current row index being processed in data-driven test
     * @return Current row index (1-based)
     */
    protected int getCurrentRowIndex() {
        return currentRowIndex.get();
    }

    /**
     * Reset row index counter (call in @BeforeClass if running multiple test classes)
     */
    protected void resetRowIndexCounter() {
        currentRowIndex.set(1);
    }


}
