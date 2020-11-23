package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

import java.io.ByteArrayInputStream;

public class MainPage extends AbstractPage {
    private Logger logger = LogManager.getLogger(MainPage.class);

    String eventsEpamUrl = "https://events.epam.com";
    By eventsPage = By.xpath("//a[@class='nav-link'][@href='/events']");

    @Step("Main page")
    public void openMainPage() {
        DriverManager.getDriver().get(eventsEpamUrl);
        Allure.addAttachment("Main page events.epam.com",
                new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
        logger.info("Page " + eventsEpamUrl + " is opened");
    }

    @Step("Upcoming events page")
    public void goToEvents() {
        waitForElement(eventsPage).click();
        waitForElement(By.xpath("//div[@class='evnt-card-wrapper']")).isDisplayed();
        Allure.addAttachment("Upcoming events",
                new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
        logger.info("Events page is opened");
    }
}
