package cases;

import helpers.DateHelpers;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import pages.EventsPage;
import utils.BaseHooks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Execution(ExecutionMode.CONCURRENT)
public class EventDateTest extends BaseHooks {
    EventsPage eventsPage = new EventsPage();
    DateHelpers dateHelpers = new DateHelpers();

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
