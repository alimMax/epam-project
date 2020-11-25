package cases;

import helpers.DateHelpers;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.EventsPage;
import pages.MainPage;
import utils.BaseHooks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventsTest  extends BaseHooks {
    MainPage mainPage = new MainPage();
    EventsPage eventsPage = new EventsPage();
    DateHelpers dateHelpers = new DateHelpers();

    @Test
    @Epic("Events.EPAM")
    @Feature("Counter")
    @Description("Compare upcoming events counter and all cards counter in the page")
    public void upcomingEvents() {
        mainPage.openMainPage();
        mainPage.goToEvents();
        eventsPage.clickUpcomingEvents();
        int cardsSize = eventsPage.getEventsCards().size();
        Assert.assertEquals(Integer.toString(cardsSize), eventsPage.getEventsCounter());
    }

    @Test
    @Epic("Events.EPAM")
    @Feature("Card Info")
    @Description("Get event card info and compare with expectations")
    public void viewCardInfo() {
        eventsPage.open();
        eventsPage.clickUpcomingEvents();

        // get webinarCard infos
        WebElement card = eventsPage.getDevopsWebinarCard();
        WebElement location = card.findElement(By.xpath(".//p[@class='online']"));
        WebElement lang = card.findElement(By.xpath(".//p[@class='language']"));
        WebElement title = card.findElement(By.xpath(".//div[@class='evnt-event-name']"));
        WebElement date = card.findElement(By.xpath(".//span[@class='date']"));
        WebElement regStatus = card.findElement(By.xpath(".//span[@class='status free-attend']"));
        WebElement speakers = card.findElement(By.xpath(".//div[@class='evnt-speaker']"));

        Assert.assertEquals("Online only", location.getText());
        Assert.assertEquals("Ru", lang.getText());
        Assert.assertEquals("DevOps Architecture Webinar", title.getText());
        Assert.assertEquals("17 Dec 2020", date.getText());
        Assert.assertEquals("Registration opened", regStatus.getText());
        Assert.assertEquals("Aleksandr Chikovani", speakers.getAttribute("data-name"));
        Assert.assertEquals("EPAM, Senior Systems Engineer", speakers.getAttribute("data-job-title"));
    }

    @Test
    @Epic("Events.EPAM")
    @Feature("Date")
    @Description("Validating upcoming events date")
    public void validateDate() {
        eventsPage.open();
        eventsPage.clickUpcomingEvents();
        String cardDate = eventsPage.getHRMeetupCard().findElement(By.xpath(".//span[@class='date']")).getText();

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate formattedDate = LocalDate.parse(cardDate, pattern);

        Assert.assertTrue(dateHelpers.isDataEqualAndAfterCurrent(formattedDate));
        Assert.assertTrue(dateHelpers.isDateValidCurrentWeek(formattedDate));
    }
}
