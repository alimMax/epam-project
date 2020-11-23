package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.EventsPage;
import utils.BaseHooks;

@Execution(ExecutionMode.CONCURRENT)
public class ReviewEventCardTest extends BaseHooks {
    EventsPage eventsPage = new EventsPage();

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
}
