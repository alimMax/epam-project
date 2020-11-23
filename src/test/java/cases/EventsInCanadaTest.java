package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.EventsPage;
import utils.BaseHooks;

@Execution(ExecutionMode.CONCURRENT)
public class EventsInCanadaTest extends BaseHooks {
    EventsPage eventsPage = new EventsPage();

    @Test
    @Epic("Events.EPAM")
    @Feature("Past events filter")
    @Description("Get past events by filter and compare cards count")
    public void getCanadaEvents() {
        eventsPage.open();
        eventsPage.clickPastEvents();
        eventsPage.filterByLocation();
        int cardsSize = eventsPage.getEventsCards().size();
        Assert.assertEquals(Integer.toString(cardsSize), eventsPage.getPastEventsCounter());
    }
}
