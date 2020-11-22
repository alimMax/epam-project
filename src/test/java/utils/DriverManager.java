package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;

    public DriverManager() {}

    @SneakyThrows
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        /*String selenoidUrl = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("86.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1280x1024");
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("enableLog", true);

        driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);*/
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
