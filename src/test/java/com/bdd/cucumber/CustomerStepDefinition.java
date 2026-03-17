package com.bdd.cucumber;

import com.gurubank.commons.DriverManager;
import com.gurubank.pages.BasePage;
import com.gurubank.pages.HomePage;
import com.gurubank.pages.LoginPage;
import com.gurubank.pages.NewCustomerPage;
import com.gurubank.utils.ReadTestProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CustomerStepDefinition {

    HomePage homePage = new HomePage(DriverManager.getDriver());
    NewCustomerPage newCustomerPage = new NewCustomerPage(DriverManager.getDriver());

    @Given("user is on the Create {string} page")
    public void userIsOnThePage(String pageName) {
        homePage.clickBankerOption("New Customer");
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
    @Then("the user saves customer Id in test properties file for future use")
    public void theUserSavesCustomerIdInTestPropertiesFileForFutureUse() {
        Assert.assertNotNull(newCustomerPage.getCustomerId(), "Customer ID is null");
        ReadTestProperties.setTestPropertyValue("customer.id", newCustomerPage.getCustomerId());
    }


}
