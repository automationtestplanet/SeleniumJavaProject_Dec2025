package com.gurubank.demo;

import com.gurubank.pages.LoginPage;
import com.gurubank.pages.OpenMrsLoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;

public class WelcomePageTest2 {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(System.getProperty("user.dir")+"//src//main//resources//Welcome.html");
        Thread.sleep(5000);

        WebElement userNameField = driver.findElement(By.id("userName"));
        userNameField.sendKeys("mngr650380");

        WebElement passwordField = driver.findElement(By.name("Password"));
        passwordField.sendKeys("gAqysAh");

        WebElement genderMaleRadioButton = driver.findElement(By.id("male"));
        System.out.println("Selected?: "+genderMaleRadioButton.isSelected());
        if(! genderMaleRadioButton.isSelected()){
            genderMaleRadioButton.click();
        }

        WebElement termsCheckBox = driver.findElement(By.className("checkbox"));
        System.out.println("Selected?: "+termsCheckBox.isSelected());
        if(! termsCheckBox.isSelected()){
            termsCheckBox.click();
        }

        WebElement textArea = driver.findElement(By.tagName("textarea"));
        textArea.sendKeys("Hello this is Selenium WebDriver class");
        Thread.sleep(3000);

        WebElement firstNameField = driver.findElement(By.xpath("//label[*[text()='First Name']]//following-sibling::input[not(@hidden)][1]"));

        Actions actions = new Actions(driver);
//        actions.click(firstNameField).sendKeys("Hello").sendKeys(Keys.CONTROL+"a").sendKeys(Keys.DELETE).sendKeys("Java")
//                .build().perform();
//        actions.click(firstNameField).sendKeys("Hello").sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.DELETE).sendKeys("Java")
//                .build().perform();

        actions.click(firstNameField).sendKeys("Hello").doubleClick(firstNameField).sendKeys(Keys.DELETE).sendKeys("Java")
                .build().perform();

        WebElement linkSameWindow = driver.findElement(By.linkText("OpenMRS in Same Window"));
        actions.moveToElement(linkSameWindow).contextClick(linkSameWindow).build().perform();


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[*[text()='Name4']]//following-sibling::input")).sendKeys("Name4");
        Thread.sleep(2000);
        jse.executeScript("window.scroll(0,document.body.scrollLeft)");

//        String mainWindowId = driver.getWindowHandle();
//        System.out.println("Main Window ID: "+mainWindowId);
//        driver.findElement(By.linkText("OpenMRS in New Tab")).click();
//
//         Set<String> allWinIds = driver.getWindowHandles();
//        System.out.println("All Window IDs: "+allWinIds);
//
//        for(String eachWinId : allWinIds){
//            if(!eachWinId.equals(mainWindowId)){
//                driver.switchTo().window(eachWinId);
//                Thread.sleep(5000);
//                OpenMrsLoginPage openMrs = new OpenMrsLoginPage(driver);
//                openMrs.loginToOpenMrs("Admin", "Admin123");
//                driver.close();
//                driver.switchTo().window(mainWindowId);
//                break;
//            }
//        }
//
//
//
//        driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();
//        Set<String> allWinIds2 = driver.getWindowHandles();
//        System.out.println("All Window IDs: "+allWinIds2);
//        for(String eachWinId : allWinIds2){
//            if(!eachWinId.equals(mainWindowId)){
//                driver.switchTo().window(eachWinId);
//                driver.manage().window().maximize();
//                Thread.sleep(5000);
//                OpenMrsLoginPage openMrs = new OpenMrsLoginPage(driver);
//                openMrs.loginToOpenMrs("Admin", "Admin123");
//                driver.close();
//                driver.switchTo().window(mainWindowId);
//                break;
//            }
//        }
//
//        driver.navigate().refresh();
//        Thread.sleep(3000);
//
//        driver.switchTo().frame("frame1");
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.loginToGuruBank("mngr655463", "bUtAvys");
//        driver.switchTo().defaultContent();

        WebElement fileUploadEle = driver.findElement(By.cssSelector("input[type='file']"));
        jse.executeScript("arguments[0].click();", fileUploadEle);
        Thread.sleep(3000);

        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\UploadFile.pdf";
        StringSelection strSelect = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelect, null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(3000);



//        driver.quit();
    }
}
