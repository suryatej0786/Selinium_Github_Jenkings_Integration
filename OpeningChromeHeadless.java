//package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpeningChromeHeadless {

    WebDriver driver;

    // This runs automatically BEFORE every @Test
    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in background for Jenkins
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // This is the actual Test Case
    @Test
    public void verifyGoogleTitle() {
        System.out.println("--- Starting Test: verifyGoogleTitle ---");

        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page Title Found: " + title);

        // Assertion: If this fails, Jenkins marks the build as FAILED automatically
        Assert.assertTrue(title.contains("Google"), "Title did not contain 'Google'!");
    }

    // This runs automatically AFTER every @Test
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("--- Test Finished & Driver Closed ---");
    }
}