package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    @BeforeMethod
    public void setup() {
        // Automatically downloads and sets up the correct ChromeDriver version
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Required for Jenkins (no GUI)
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyGoogleTitle() {
        System.out.println("--- Starting Test: verifyGoogleTitle ---");

        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page Title Found: " + title);

        // Assertion: If this fails, Jenkins marks the build as FAILED
        Assert.assertTrue(title.contains("Google"), "Title did not contain 'Google'!");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("--- Test Finished & Driver Closed ---");
    }
}
