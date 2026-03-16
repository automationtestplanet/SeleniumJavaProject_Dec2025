package com.gurubank;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class GuruBankDataDrivenTest extends BaseTest {

    @Test(priority = 0, dataProvider = "DataDrivenTestData")
    public void createNewCustomer(Map<String, String> testData) {
        homePage.clickBankerOption("New Customer");
        Assert.assertTrue(homePage.verifyPageHeading("New Customer"), "New Customer page is not displayed");
        newCustomerPage.createNewCustomer(testData.get("Name"), testData.get("Gender"), testData.get("DateOfBirth"), testData.get("Address"), testData.get("City"), testData.get("State"), testData.get("PinCode"), testData.get("PhoneNumber"), testData.get("Email"), testData.get("Password"));
        Assert.assertTrue(newCustomerPage.verifyPageHeading("Customer Registered Successfully!!!"), "Customer Registration was not successful");
        Assert.assertTrue(newCustomerPage.verifyNewCustomerDetails(testData.get("Name"), testData.get("PhoneNumber")), "Customer details are not correct");
        Assert.assertNotNull(newCustomerPage.getCustomerId(), "Customer ID is null");
        String generatedCustomerId = newCustomerPage.getCustomerId();
        updateCustomerIdInExcel(generatedCustomerId);
    }

    @Test(priority = 1, dataProvider = "DataDrivenTestData", dependsOnMethods = "createNewCustomer")
    public void deleteCustomer(Map<String, String> testData) {
        homePage.clickBankerOption("Delete Customer");
        Assert.assertTrue(homePage.verifyPageHeading("Delete Customer Form"), "Delete Customer page is not displayed");
        String customerId = testData.get("CustomerId");
        Assert.assertNotNull(customerId, "CustomerId from test data is null");
        deleteCustomerPage.deleteCustomer(customerId);
    }
}
