package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.util.List;

public class EventsPage extends AbstractPage {
    private final Logger logger = LogManager.getLogger(EventsPage.class);

    String url = "https://events.epam.com/events";
    By upcomingEvents = By.xpath("//span[@class='evnt-tab-text desktop'][text()='Upcoming events']");
    By pastEvents = By.xpath("//span[@class='evnt-tab-text desktop'][text()='Past Events']");
    By eventsCounter = By.xpath("//li[@class='evnt-tab-item nav-item']//span[@class='evnt-tab-counter evnt-label small white']");
    By pastEventsCounter = By.xpath("//a[@class='evnt-tab-link nav-link active']//span[@class='evnt-tab-counter evnt-label small white']");
    By eventsCard = By.xpath("//div[@class='evnt-events-column cell-3']");
    By devopsWebinarCard = By.xpath("//a[@href='/events/devops-architecture-webinar']");
    By hrMeetupCard = By.xpath("//a[@href='/events/hr-meetup-online']");
    By filterByLocation = By.xpath("//div[@id='filter_location']");

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Events page")
    public void open() {
        driver.get(url);
        Allure.addAttachment("Events page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Page " + url + " is opened");
    }

    public void clickUpcomingEvents() {
        waitForElement(upcomingEvents).click();
    }

    @Step("Past events")
    public void clickPastEvents() {
        waitForElement(pastEvents).click();
        logger.info("Past events");
    }

    public String getEventsCounter() {
        return waitForElement(eventsCounter).getText();
    }

    public List<WebElement> getEventsCards() {
        return waitForElements(eventsCard);
    }

    public WebElement getDevopsWebinarCard() {
        return waitForElement(devopsWebinarCard);
    }

    public WebElement getHRMeetupCard() {
        return waitForElement(hrMeetupCard);
    }

    @Step("Filtering videso by location")
    public void filterByLocation() {
        waitForElement(filterByLocation).click();
        waitForElement(By.xpath("//div[@data-group='Canada']//label[@for='filter_location_3']")).click();
        waitForElement(filterByLocation).click();
        Allure.addAttachment("Videos filtered by location",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public String getPastEventsCounter() {
        return waitForElement(pastEventsCounter).getText();
    }
}
