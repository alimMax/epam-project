package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElement(By element) {
        WebDriverWait waitElement = new WebDriverWait(driver, 10);
        waitElement.until(ExpectedConditions.presenceOfElementLocated(element));
        return driver.findElement(element);
    }

    protected List<WebElement> waitForElements(By elements) {
        WebDriverWait waitElement = new WebDriverWait(driver, 10);
        waitElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elements));
        return driver.findElements(elements);
    }
}
