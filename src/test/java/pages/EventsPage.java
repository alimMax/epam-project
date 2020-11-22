package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EventsPage extends AbstractPage {
    By upcomingEvents = By.xpath("//a[@class='evnt-tab-link nav-link']");
    By eventsCounter = By.xpath("//a[@class='evnt-tab-link nav-link']//span[@class='evnt-tab-counter evnt-label small white']");
    By eventsCard = By.xpath("//div[@class='evnt-events-column cell-3']");

    public void clickUpcomingEvents() {
        waitForElement(upcomingEvents).click();
    }

    public String getEventsCounter() {
        return waitForElement(eventsCounter).getText();
    }

    public List<WebElement> getEventsCards() {
        return waitForElements(eventsCard);
    }
}
