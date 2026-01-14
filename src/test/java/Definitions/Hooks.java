package Definitions;

import Support.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    @Before(order = 0)
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "true")
        );

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        // ✅ ÚNICA fuente de verdad
        DriverFactory.setDriver(driver);
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {

            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed");
            }

            DriverFactory.quitDriver();
        }
    }
}
