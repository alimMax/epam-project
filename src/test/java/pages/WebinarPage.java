package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebinarPage extends AbstractPage {
    By registerButton= By.xpath("//button[@id='no_reg_5729']");
    By eventDate = By.xpath("//span[@class='date']");
    By title = By.xpath("//div[@class='evnt-content-cell info']//h2[2]");

    public WebElement getRegButton() {
        return waitForElement(registerButton);
    }

    public WebElement getEventDate() {
        return waitForElement(eventDate);
    }

    public WebElement getTitle() {
        return waitForElement(title);
    }
}
