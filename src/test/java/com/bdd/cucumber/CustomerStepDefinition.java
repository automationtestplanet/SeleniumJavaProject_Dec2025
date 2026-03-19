package com.bdd.cucumber;

import com.gurubank.commons.Commons;
import com.gurubank.commons.DriverManager;
import com.gurubank.pages.*;
import com.gurubank.utils.ReadTestProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CustomerStepDefinition {

    HomePage homePage = new HomePage(DriverManager.getDriver());
    NewCustomerPage newCustomerPage = new NewCustomerPage(DriverManager.getDriver());
    DeleteCustomerPage deleteCustomerPage = new DeleteCustomerPage(DriverManager.getDriver());

    @Given("user is on the Create {string} page")
    public void userIsOnTheCreatePage(String pageName) {
        homePage.clickBankerOption(pageName);
        Assert.assertTrue(homePage.verifyPageHeading(pageName), "New Customer page is not displayed");
    }
    @When("the user enters Name as {string}, Gender as {string}, DateOfBirth as {string}, Address as {string}, City as {string}, State as {string}, PinCode as {string}, Mobile Number as {string}, Email as {string} and Password as {string}")
    public void theUserEntersNameAsGenderAsDateOfBirthAsAddressAsCityAsStateAsPinCodeAsMobileNumberAsEmailAsAndPasswordAs(String name, String gender, String dob, String address, String city, String state, String pinCode, String phoneNum, String email, String password) {
        newCustomerPage.setCustomerName(name);
        newCustomerPage.selectGender(gender);
        newCustomerPage.setDob(dob);
        newCustomerPage.setAddress(address);
        newCustomerPage.setCity(city);
        newCustomerPage.setState(state);
        newCustomerPage.setPinCode(pinCode);
        newCustomerPage.setPhoneNum(phoneNum);
        newCustomerPage.setEmail(email);
        newCustomerPage.setPasswordForCustomer(password);
    }
    @When("clicks on the submit button")
    public void clicksOnTheButton() {
        newCustomerPage.clickSubmitButton();
        newCustomerPage.refreshPage();
    }
    @Then("a new customer account should be created successfully")
    public void aNewCustomerAccountShouldBeCreatedSuccessfully() {
        Assert.assertTrue(newCustomerPage.verifyPageHeading("Customer Registered Successfully!!!"), "Customer Registration was not successful");
    }
    @Then("Customer Name {string} and PhoneNumber {string} should be displayed")
    public void customerNameAndPhoneNumberShouldBeDisplayed(String name, String phoneNum) {
        Assert.assertTrue(newCustomerPage.verifyNewCustomerDetails(name, phoneNum), "Customer details are not correct");
    }
    @Then("the user saves customer Id as {string} in test properties file for future use")
    public void theUserSavesCustomerIdInTestPropertiesFileForFutureUse(String customerIdKey) {
        Assert.assertNotNull(newCustomerPage.getCustomerId(), "Customer ID is null");
        String customerId = newCustomerPage.getCustomerId();
        ReadTestProperties.setTestPropertyValue(customerIdKey.replaceAll(" ","."), customerId);
    }

    @Given("user is on the {string} page")
    public void userIsOnThePage(String pageName) {
        homePage.clickBankerOption(pageName);
        Assert.assertTrue(homePage.verifyPageHeading("Delete Customer Form"), "Delete Customer page is not displayed");
    }
    @When("the user enters customer Id {string}")
    public void theUserEntersCustomerId(String customerId) {
        String retrievedCustomerId = ReadTestProperties.getTestPropertyValue(customerId.replaceAll(" ","."));
        Assert.assertNotNull(retrievedCustomerId,
            "Customer ID not found in properties for key: " + customerId);
        deleteCustomerPage.setCustomerId(retrievedCustomerId);
    }
    @When("clicks on the delete submit button")
    public void clicksOnTheDeleteSubmitButton() {
        deleteCustomerPage.clickDeleteCustomerSubmitButton();
        deleteCustomerPage.acceptAlert();
        deleteCustomerPage.refreshPage();
        Assert.assertTrue(deleteCustomerPage.getAlertMessage().contains("Customer does not exist"), "Customer was not deleted successfully");
    }
    @Then("a customer should be deleted successfully")
    public void aCustomerShouldBeDeletedSuccessfully() {
        deleteCustomerPage.acceptAlert();
    }
}
