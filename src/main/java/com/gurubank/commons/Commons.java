package com.gurubank.commons;

import com.gurubank.utils.ReadConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commons {

    public static void captureScreenShot() {
        TakesScreenshot ts = (TakesScreenshot)DriverManager.getDriver();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
            try {
                String screenshotName = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replaceAll("[^0-9]","")+".png";
                String screenshotPath = System.getProperty("user.dir") + ReadConfigProperties.getConfigPropertyValue("screenshot.path") + screenshotName ;
                File destinationFile = new File(screenshotPath);
                FileUtils.copyFile(screenshot,destinationFile);
            } catch (Exception e) {
                System.out.println("Exception occurred while capturing screenshot : " + e.getMessage());
            }
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
