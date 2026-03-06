package com.gurubank.demo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class GuruBankTest {
    public static void main(String[] args) throws Exception {
//        ChromeDriver driver = new ChromeDriver();
//        EdgeDriver driver = new EdgeDriver();

//        WebDriver driver = new EdgeDriver();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demo.guru99.com/V4/index.php");

//        driver.get("https://demo.guru99.com/V1/index.php");

        Thread.sleep(5000);
        String title = driver.getTitle();
        System.out.println("Title is: " + title);

        String currURL = driver.getCurrentUrl();
        System.out.println("Current URL is: " + currURL);

//        driver.navigate().back();
//        Thread.sleep(2000);
//        driver.navigate().forward();
//        Thread.sleep(2000);
//        driver.navigate().refresh();
//        Thread.sleep(2000);

        String pageSource = driver.getPageSource();
//        System.out.println("Page source is: " + pageSource);

//        driver.findElement("");  -> WebElement
//        driver.findElements("");  -> List<WebElement>

        WebElement userNameField = driver.findElement(By.name("uid"));
        String type = userNameField.getAttribute("type");
        System.out.println("Type of user name field is: " + type);
        System.out.println("TagName: " + userNameField.getTagName());
        System.out.println("MaxLength: " + userNameField.getAttribute("maxlength"));
        System.out.println("OnKeuUp: " + userNameField.getAttribute("onkeyup"));
        System.out.println("OnBlur: " + userNameField.getAttribute("onblur"));

        System.out.println("Enabled? : " + userNameField.isEnabled());
        System.out.println("Displayed? : " + userNameField.isDisplayed());
        userNameField.sendKeys("mngr655463");
        driver.findElement(By.name("password")).sendKeys("bUtAvys");

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        System.out.println("Login button Name : " + loginButton.getAttribute("value"));
        loginButton.click();
        Thread.sleep(5000);


        if (driver.getTitle().contains("HomePage")) {
            String managerIdText = driver.findElement(By.xpath("//td[contains(text(),'Manger Id :')]")).getText();
            System.out.println("Manager ID Text: " + managerIdText);

            if (managerIdText.contains("mngr655463")) {
                driver.findElement(By.linkText("New Customer")).click();
                Thread.sleep(2000);

                if (driver.findElement(By.xpath("//p[text()='Add New Customer']")).isDisplayed()) {
                    driver.findElement(By.name("name")).sendKeys("Test Customer");

                    WebElement genderMaleRadioButton = driver.findElement(By.xpath("//input[@value='m']"));
                    if (!genderMaleRadioButton.isSelected()) {
                        genderMaleRadioButton.click();
                    }
                    driver.findElement(By.id("dob")).sendKeys("2020-03-06");
                    driver.findElement(By.name("addr")).sendKeys("HNO 25 S R Nagar");
                    driver.findElement(By.name("city")).sendKeys("Hyderabad");
                    driver.findElement(By.name("state")).sendKeys("Telangana");
                    driver.findElement(By.name("pinno")).sendKeys("500038");
                    driver.findElement(By.name("telephoneno")).sendKeys("9876543210");
                    driver.findElement(By.name("emailid")).sendKeys("customer689@gmail.com");
                    driver.findElement(By.name("password")).sendKeys("TestCustomer@123");
                    driver.findElement(By.name("sub")).click();
                    Thread.sleep(2000);
                    driver.navigate().refresh();
                    Thread.sleep(2000);

                    if (driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed()) {
                        String customerId = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
                        System.out.println("Customer ID: " + customerId);

                        String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText();
                        String mobileNumber = driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText();

                        if (customerName.equals("Test Customer") && mobileNumber.equals("9876543210")) {
                            System.out.println("Customer Registration is successful and details are correct");

                            driver.findElement(By.linkText("Delete Customer")).click();

                            Thread.sleep(2000);
                            if (driver.findElement(By.xpath("//p[text()='Delete Customer Form']")).isDisplayed()) {
                                driver.findElement(By.name("cusid")).sendKeys(customerId);
                                driver.findElement(By.name("AccSubmit")).click();
                                Thread.sleep(2000);

                                Alert alert = driver.switchTo().alert();
                                System.out.println("Alert Text: " + alert.getText());
                                alert.accept();
                                Thread.sleep(2000);
                                driver.navigate().refresh();
                                Thread.sleep(2000);
                                driver.switchTo().alert().accept();
                                Thread.sleep(2000);
                            }

                        } else {
                            System.out.println("Customer Registration is successful but details are not correct");
                        }

                        driver.findElement(By.linkText("Log out")).click();
                        Thread.sleep(2000);
                        driver.switchTo().alert().accept();
                        Thread.sleep(2000);


                    } else {
                        System.out.println("Customer Registration is not successful");
                    }


                } else {
                    System.out.println("Add New Customer page is not displayed");
                }

            } else {
                System.out.println("Manager ID is not correct");
            }
        } else {
            System.out.println("HomePage is Not Displayed");
        }


        driver.close();


//        driver.quit();
    }
}
