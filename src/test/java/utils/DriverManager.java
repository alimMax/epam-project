package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    protected  WebDriver driver;

    @SneakyThrows
    @BeforeEach
    public void setupDriver() {
        /*WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();*/

        String selenoidUrl = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(System.getProperty("browser"));
        capabilities.setVersion(System.getProperty("version"));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1280x1024");
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("enableLog", true);

        driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
