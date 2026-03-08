package com.gurubank.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GuruBankTestWithModularMethods {

    public static WebDriver driver;

    public void verifyPageTitle(String expectedPageTitle) {
        if (driver.getTitle().contains(expectedPageTitle)) {
            System.out.println(expectedPageTitle + " is displayed");
        } else {
            System.out.println(expectedPageTitle + " is not displayed");
        }
    }

    public void launchApplication(String url, String expectedPageTitle) throws Exception {
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        verifyPageTitle(expectedPageTitle);
    }

    public void setUserName(String userName) {
        driver.findElement(By.name("uid")).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.name("btnLogin")).click();
    }

    public void verifyManagerId(String expManagerId) throws InterruptedException {
        String managerIdText = driver.findElement(By.xpath("//td[contains(text(),'Manger Id :')]")).getText();
        System.out.println("Manager ID Text: " + managerIdText);

        if (managerIdText.contains(expManagerId)) {
            System.out.println("Manager ID is correct");
        } else {
            System.out.println("Manager ID is not correct");
        }
    }

    public void loginToGuruBank(String userName, String password) throws Exception {
        setUserName(userName);
        setPassword(password);
        clickLoginButton();
        Thread.sleep(5000);
        verifyManagerId(userName);
    }

    public void verifyPageHeading(String headingName) {
        if (driver.findElement(By.xpath("//p[contains(text(),'" + headingName + "')]")).isDisplayed()) {
            System.out.println(headingName + " page is displayed");
        } else {
            System.out.println(headingName + " page is not displayed");
        }
    }

    public void clickBankerOption(String optionName) throws InterruptedException {
        driver.findElement(By.linkText(optionName)).click();
        Thread.sleep(2000);
        verifyPageHeading(optionName);
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
        driver.findElement(By.name("sub")).click();
    }

    public void createNewCustomer(String name, String gender, String dob, String address, String city, String state, String pinCode, String phoneNum, String email, String password) throws InterruptedException {
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
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    public void verifyNewCustomerDetails(String expCustomerName, String expMobileNumber) {
        String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText();
        String mobileNumber = driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText();
        if (customerName.equals(expCustomerName) && mobileNumber.equals(expMobileNumber)) {
            System.out.println("Customer Registration is successful and details are correct");
        } else {
            System.out.println("Customer Registration is successful but details are not correct");
        }
    }

    public String getCustomerId() {
        return driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText().trim();
    }

    public void setCustomerId(String customerId) {
        driver.findElement(By.name("cusid")).sendKeys(customerId);
    }

    public void clickDeleteCustomerSubmitButton() {
        driver.findElement(By.name("AccSubmit")).click();
    }

    public void acceptAlert() throws InterruptedException {
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }

    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
    }


    public void deleteCustomer(String customerId) throws InterruptedException {
        setCustomerId(customerId);
        clickDeleteCustomerSubmitButton();
        Thread.sleep(2000);
        acceptAlert();
        refreshPage();
        acceptAlert();
    }

    public void clickUsingJavaScriptExecutor(WebElement element) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
    }

    public void logoutFromGuruBank() throws InterruptedException {
        WebElement logOut = driver.findElement(By.linkText("Log out"));
        clickUsingJavaScriptExecutor(logOut);
        acceptAlert();
    }

    public void closeApplication() {
        driver.close();
    }

    public static void main(String[] args) throws Exception {
        GuruBankTestWithModularMethods guruBank = new GuruBankTestWithModularMethods();
        driver = new ChromeDriver();
        guruBank.launchApplication("https://demo.guru99.com/V4/index.php", "Guru99 Bank Home Page");
        guruBank.loginToGuruBank("mngr655463", "bUtAvys");
        guruBank.clickBankerOption("New Customer");
        guruBank.createNewCustomer("Test Customer", "Male", "2020-03-06", "HNO 25 S R Nagar", "Hyderabad", "Telangana", "500038", "9876543210", "customer689@gmail.com", "TestCustomer@123");
        guruBank.verifyPageHeading("Customer Registered Successfully!!!");
        guruBank.verifyNewCustomerDetails("Test Customer", "9876543210");
        String customerId = guruBank.getCustomerId();
        System.out.println("Customer ID: " + customerId);
        guruBank.clickBankerOption("Delete Customer");
        Thread.sleep(2000);
        guruBank.verifyPageHeading("Delete Customer Form");
        guruBank.deleteCustomer(customerId);
        guruBank.logoutFromGuruBank();
        guruBank.closeApplication();
    }

}
