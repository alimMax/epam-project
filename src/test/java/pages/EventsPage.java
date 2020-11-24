package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverManager;

import java.util.List;

public class EventsPage extends AbstractPage {
    String url = "https://events.epam.com/events";
    By upcomingEvents = By.xpath("//span[@class='evnt-tab-text desktop'][text()='Upcoming events']");
    By pastEvents = By.xpath("//span[@class='evnt-tab-text desktop'][text()='Past Events']");
    By eventsCounter = By.xpath("//a[@class='evnt-tab-link nav-link']//span[@class='evnt-tab-counter evnt-label small white']");
    By pastEventsCounter = By.xpath("//a[@class='evnt-tab-link nav-link active']//span[@class='evnt-tab-counter evnt-label small white']");
    By eventsCard = By.xpath("//div[@class='evnt-events-column cell-3']");
    By devopsWebinarCard = By.xpath("//a[@href='/events/devops-architecture-webinar']");
    By hrMeetupCard = By.xpath("//a[@href='/events/hr-meetup-online']");
    By filterByLocation = By.xpath("//div[@id='filter_location']");

    public void open() {
        DriverManager.getDriver().get(url);
    }

    public void clickUpcomingEvents() {
        waitForElement(upcomingEvents).click();
    }

    public void clickPastEvents() {
        waitForElement(pastEvents).click();
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

    public void filterByLocation() {
        waitForElement(filterByLocation).click();
        waitForElement(By.xpath("//div[@data-group='Canada']//label[@for='filter_location_3']")).click();
        waitForElement(filterByLocation).click();
    }

    public String getPastEventsCounter() {
        return waitForElement(pastEventsCounter).getText();
    }
}
