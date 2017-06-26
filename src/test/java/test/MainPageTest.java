package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import static java.lang.Thread.sleep;

/**
 * Created by SvetLana on 22.06.2017.
 */
public class MainPageTest {
    public WebDriver webDriver;
    MainPage mainPage;
    public String username = "denvert1@shotspotter.net";
    public String password = "Test123!";

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz");
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(username, password);
    }
    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }

    @Test
    public void testSwitchIncidentsPeriod() {

        int[] timeFrameOptions = {24,3,7};
        for(int timeFrameOption : timeFrameOptions){
            mainPage.switchTimeFramePeriod(timeFrameOption);
            int resultsCount = mainPage.getResultsCount();
            int incidentCardsCount = mainPage.getIncidentCardsCount();

            System.out.println("Preriod: "+timeFrameOption);
            System.out.println("resultsCount: "+resultsCount);
            System.out.println("incidentCardsCount: "+incidentCardsCount);
            Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");
        }
    }

    @DataProvider
    public static Object[][] timeFrameOptions(){
        return new Object[][]{ {24},{3},{7}};
    }

    @Test(dataProvider = "timeFrameOptions")
    public void testSwitchIncidentsPeriodByDataProvider(int timeFrameOption) {
        mainPage.switchTimeFramePeriod(timeFrameOption);
        int resultsCount = mainPage.getResultsCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();

        System.out.println("Period: "+timeFrameOption);
        System.out.println("resultsCount: "+resultsCount);
        System.out.println("incidentCardsCount: "+incidentCardsCount);
        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");

    }
}
