package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.EventsPage;
import pages.WebinarPage;
import utils.DriverManager;

import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
public class DetailedEventsTest extends DriverManager {
    EventsPage eventsPage;
    WebinarPage webinarPage;

    @Test
    @Epic("Events.EPAM")
    @Feature("Past events filter")
    @Description("Get past events by filter and compare cards count")
    public void getCanadaEvents() {
        eventsPage = new EventsPage(driver);

        eventsPage.open();
        eventsPage.clickPastEvents();
        eventsPage.filterByLocation();
        List<WebElement> card = eventsPage.getEventsCards();
        int cardsSize = card.size();
        Assert.assertEquals(Integer.toString(cardsSize), eventsPage.getPastEventsCounter());
    }

    @Test
    @Epic("Events.EPAM")
    @Feature("Datailed info")
    @Description("Check detailed info of the upcoming event")
    public void detailedInfo() {
        eventsPage = new EventsPage(driver);
        webinarPage = new WebinarPage(driver);

        eventsPage.open();
        eventsPage.clickUpcomingEvents();
        WebElement card = eventsPage.getDevopsWebinarCard().findElement(By.xpath(".//div[@class='evnt-card-body']"));

        // using JSExecutor
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(894, 900)");
        card.click();

        webinarPage.getRegButton().isDisplayed();
        Assert.assertEquals("17 Dec 2020, 22:00 - 23:00", webinarPage.getEventDate().getText());
        Assert.assertEquals(
                "Сделай сам без клея и ножниц, или конструктор для дата-платформы",
                webinarPage.getTitle().getText()
        );
    }
}
