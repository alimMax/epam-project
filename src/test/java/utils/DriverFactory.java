package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    @SneakyThrows
    public static WebDriver createDriver(String platform) {
        WebDriver driver = null;

        switch (platform.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "selenoid":
                String selenoidUrl = "http://localhost:4444/wd/hub";
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(System.getProperty("browser"));
                capabilities.setVersion(System.getProperty("version"));
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("screenResolution", "1280x1024");
                capabilities.setCapability("enableVideo", false);
                capabilities.setCapability("enableLog", true);
                capabilities.setCapability("timezone", "Asia/Almaty");

                driver = new RemoteWebDriver(new URL(selenoidUrl), capabilities);
                break;
        }
        return driver;
    }

    public static String getPlatform() {
        String systemEnv = System.getProperty("platform");
        if (systemEnv == null) {
            return "chrome";
        }
        else {
            return systemEnv;
        }
    }
}
