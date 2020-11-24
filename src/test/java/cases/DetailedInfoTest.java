package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.EventsPage;
import pages.WebinarPage;
import utils.BaseHooks;
import utils.DriverManager;

@Execution(ExecutionMode.CONCURRENT)
public class DetailedInfoTest extends BaseHooks {
    EventsPage eventsPage = new EventsPage();
    WebinarPage webinarPage = new WebinarPage();

    @Test
    @Epic("Events.EPAM")
    @Feature("Datailed info")
    @Description("Check detailed info of the upcoming event")
    public void detailedInfo() {
        eventsPage.open();
        eventsPage.clickUpcomingEvents();
        WebElement card = eventsPage.getDevopsWebinarCard().findElement(By.xpath(".//div[@class='evnt-card-body']"));

        // using JSExecutor
        JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();
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
