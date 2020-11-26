package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    protected  WebDriver driver;

    @BeforeEach
    public void setupDriver() {
        String platform = DriverFactory.getPlatform();
        driver = DriverFactory.createDriver(platform);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
