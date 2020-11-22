package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.util.List;

public abstract class AbstractPage {
    protected WebElement waitForElement(By element) {
        WebDriverWait waitElement = new WebDriverWait(DriverManager.getDriver(), 10);
        waitElement.until(ExpectedConditions.presenceOfElementLocated(element));
        return DriverManager.getDriver().findElement(element);
    }

    protected List<WebElement> waitForElements(By elements) {
        WebDriverWait waitElement = new WebDriverWait(DriverManager.getDriver(), 10);
        waitElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elements));
        return DriverManager.getDriver().findElements(elements);
    }
}
