package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.MainPage;
import pages.VideoPage;
import utils.BaseHooks;

@Execution(ExecutionMode.CONCURRENT)
public class FilterVideoTest extends BaseHooks {
    MainPage mainPage = new MainPage();
    VideoPage videoPage = new VideoPage();

    @Test
    @Epic("Events.EPAM")
    @Feature("Vide filter")
    @Description("Filtering video by parameters")
    public void filterVideo() {
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

}
