package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.util.List;

public class VideoPage extends AbstractPage{
    By moreFilters = By.xpath("//div[@class='evnt-toggle-filters-button evnt-button btn']");
    By categoryButton = By.xpath("//div[@id='filter_category']");
    By locationButton = By.xpath("//div[@id='filter_location']");
    By languageButton = By.xpath("//div[@id='filter_language']");
    By testingVariant = By.xpath("//label[@data-value='Testing']");
    By BelarusVariant = By.xpath("//label[@data-value='Belarus']");
    By englishVariant = By.xpath("//label[@data-value='ENGLISH']");
    By eventTalkCards = By.xpath("//div[@class='evnt-talk-card']");

    public void clickMoreFilters() {
        waitForElement(moreFilters).click();
    }

    public void clickCategory() {
        waitForElement(categoryButton).click();
    }

    public void clickLocation() {
        waitForElement(locationButton).click();;
    }

    public void clickLanguage() {
        waitForElement(languageButton).click();;
    }

    public void chooseTestingVariant() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);

        WebElement element = waitForElement(testingVariant);
        WebElement anotherElement = waitForElement(categoryButton);
        JavascriptExecutor jse = (JavascriptExecutor)DriverManager.getDriver();

        // scroll to Testing variant
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        // scroll back to top
        jse.executeScript("arguments[0].scrollIntoView(true);", anotherElement);
        wait.until(ExpectedConditions.elementToBeClickable(anotherElement));
        element.click();
    }

    public void chooseBelarusVariant() {
        waitForElement(BelarusVariant).click();;
    }

    public void chooseEnglishVariant() {
        waitForElement(englishVariant).click();;
    }

    public void checkAllVideoCards() {
        List<WebElement> cards = waitForElements(eventTalkCards);
        for (WebElement card : cards) {
            WebElement element = card.findElement(By.xpath(".//p[@class='language']"));
            Assert.assertEquals("En", element.getText());
        }
    }
}
