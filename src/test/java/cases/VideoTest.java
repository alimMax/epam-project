package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.MainPage;
import pages.VideoPage;
import utils.DriverManager;

@Execution(ExecutionMode.CONCURRENT)
public class VideoTest extends DriverManager {
    MainPage mainPage;
    VideoPage videoPage;

    @Test
    @Epic("Events.EPAM")
    @Feature("Video filter")
    @Description("Filtering video by parameters")
    public void filterVideo() {
        mainPage = new MainPage(driver);
        videoPage = new VideoPage(driver);

        mainPage.openMainPage();
        mainPage.goToVideo();
        videoPage.clickMoreFilters();

        videoPage.clickCategory();
        videoPage.chooseTestingVariant();

        videoPage.clickLocation();
        videoPage.chooseBelarusVariant();
        videoPage.clickLocation();

        videoPage.clickLanguage();
        videoPage.chooseEnglishVariant();
        videoPage.clickLanguage();

        videoPage.checkAllVideoCards();
    }

    @Test
    @Epic("Events.EPAM")
    @Feature("Search video")
    @Description("Searching video by name")
    public void searchVideo() {
        mainPage = new MainPage(driver);
        videoPage = new VideoPage(driver);

        mainPage.openMainPage();
        mainPage.goToVideo();

        videoPage.searchByName("QA");
        videoPage.checkCardsTitle("QA");
    }
}
