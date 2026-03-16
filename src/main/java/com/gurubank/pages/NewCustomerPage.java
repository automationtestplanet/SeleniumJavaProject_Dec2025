package com.gurubank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewCustomerPage extends BasePage {

    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }

    public void setCustomerName(String customerName) {
        driver.findElement(By.name("name")).sendKeys(customerName);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            gender = "m";
        } else if (gender.equalsIgnoreCase("Female")) {
            gender = "f";
        }
        WebElement genderMaleRadioButton = driver.findElement(By.xpath("//input[@value='" + gender + "']"));
        if (!genderMaleRadioButton.isSelected()) {
            genderMaleRadioButton.click();
        }
    }

    public void setDob(String dob) {
        driver.findElement(By.id("dob")).sendKeys(dob);
    }

    public void setAddress(String address) {
        driver.findElement(By.name("addr")).sendKeys(address);
    }

    public void setCity(String city) {
        driver.findElement(By.name("city")).sendKeys(city);
    }

    public void setState(String state) {
        driver.findElement(By.name("state")).sendKeys(state);
    }

    public void setPinCode(String pinCode) {
        driver.findElement(By.name("pinno")).sendKeys(pinCode);
    }

    public void setPhoneNum(String phoneNum) {
        driver.findElement(By.name("telephoneno")).sendKeys(phoneNum);
    }

    public void setEmail(String email) {
        driver.findElement(By.name("emailid")).sendKeys(email);
    }

    public void setPasswordForCustomer(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void clickSubmitButton() {
        try {
            driver.findElement(By.name("sub")).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking on submit button : " + e.getMessage());
        }
    }

    public void createNewCustomer(String name, String gender, String dob, String address, String city, String state, String pinCode, String phoneNum, String email, String password) {
        setCustomerName(name);
        selectGender(gender);
        setDob(dob);
        setAddress(address);
        setCity(city);
        setState(state);
        setPinCode(pinCode);
        setPhoneNum(phoneNum);
        setEmail(email);
        setPasswordForCustomer(password);
        clickSubmitButton();
        refreshPage();
    }

    public String getCustomerName() {
        return getTextFromElement("Customer Name");
    }

    public String getCustomerMobileNumber() {
        return getTextFromElement("Mobile No.");
    }

    public boolean verifyNewCustomerDetails(String expCustomerName, String expMobileNumber) {
        return getCustomerName().equals(expCustomerName) && getCustomerMobileNumber().equals(expMobileNumber);
    }

    public String getCustomerId() {
        return driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText().trim();
    }
}
