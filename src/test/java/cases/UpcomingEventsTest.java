package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.EventsPage;
import pages.MainPage;
import utils.BaseHooks;

@Execution(ExecutionMode.CONCURRENT)
public class UpcomingEventsTest extends BaseHooks {
    MainPage mainPage = new MainPage();
    EventsPage eventsPage = new EventsPage();

    @Test
    @Epic("Events.EPAM")
    @Feature("Counter")
    @Story("Upcoming events counter")
    @Description("Compare upcoming events counter and all cards counter in the page")
    public void upcomingEvents() {
        mainPage.openMainPage();
        mainPage.goToEvents();
        eventsPage.clickUpcomingEvents();
        int cardsSize = eventsPage.getEventsCards().size();
        Assert.assertEquals(Integer.toString(cardsSize), eventsPage.getEventsCounter());
    }
}
