package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.List;

public class VideoPage extends AbstractPage{
    private final Logger logger = LogManager.getLogger(VideoPage.class);

    By moreFilters = By.xpath("//div[@class='evnt-toggle-filters-button evnt-button btn']");
    By categoryButton = By.xpath("//div[@id='filter_category']");
    By locationButton = By.xpath("//div[@id='filter_location']");
    By languageButton = By.xpath("//div[@id='filter_language']");
    By testingVariant = By.xpath("//label[@data-value='Testing']");
    By BelarusVariant = By.xpath("//label[@data-value='Belarus']");
    By englishVariant = By.xpath("//label[@data-value='ENGLISH']");
    By eventTalkCards = By.xpath("//div[@class='evnt-talk-card']");
    By eventCardPageTitle = By.xpath("//div[@class='evnt-content-table']//h1[@class='evnt-talk-title']");
    By search = By.xpath("//div[@class='evnt-search-filter']//input[@type='text']");
    By videoCardPageLocation = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']//span");
    By videoCardPageLang = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']//span");
    By videoCardPageCategory = By.xpath("//div[@class='evnt-talk-details topics']");

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    public void clickMoreFilters() {
        waitForElement(moreFilters).click();
    }

    @Step("Filtering by category")
    public void clickCategory() {
        WebElement element = waitForElement(categoryButton);
        element.click();
        logger.info("Filtered by category: " + element.getText());
    }

    @Step("Filtering by location")
    public void clickLocation() {
        WebElement element = waitForElement(locationButton);
        element.click();
        logger.info("Filtered by location: " + element.getText());
    }

    @Step("Filtering by language")
    public void clickLanguage() {
        WebElement element = waitForElement(languageButton);
        element.click();
        logger.info("Filtered by language: " + element.getText());
    }

    public void chooseTestingVariant() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = waitForElement(testingVariant);
        WebElement anotherElement = waitForElement(categoryButton);
        JavascriptExecutor jse = (JavascriptExecutor)driver;

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
        int cardsCount = cards.size();
        for (int i=0; i<cardsCount; i++) {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0, 800);");

            waitForElements(eventTalkCards).get(i).click();
            String location = waitForElement(videoCardPageLocation).getText();
            Assert.assertTrue(location.contains("Belarus"));

            String language = waitForElement(videoCardPageLang).getText();
            Assert.assertTrue(language.contains("ENGLISH"));

            String category = waitForElement(videoCardPageCategory).getText();
            Assert.assertTrue(category.contains("Testing"));

            driver.navigate().back();
            waitForElement(By.xpath("//div[@class='evnt-card-body']//*[contains(text(), 'Community')]"));
        }
    }

    @Step("Search by name")
    public void searchByName(String name) {
        WebElement searchElement = waitForElement(search);
        searchElement.sendKeys(name);
        waitForElement(By.xpath("//div[@class='evnt-card-body']//*[contains(text(), 'QA')]"));
        Allure.addAttachment("Searching videos",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Checking each card")
    public void checkCardsTitle(String name) {
        List<WebElement> cards = waitForElements(eventTalkCards);
        int cardsCount = cards.size();
        for (int i=0; i<cardsCount; i++) {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0, 800);");

            waitForElements(eventTalkCards).get(i).click();
            WebElement title = waitForElement(eventCardPageTitle);
            Allure.addAttachment("Each card page",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            Assert.assertTrue(title.getText().toLowerCase().contains(name.toLowerCase()));

            driver.navigate().back();
            waitForElement(By.xpath("//div[@class='evnt-card-body']//*[contains(text(), 'QA')]"));
        }
    }
}
