package test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;
import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by Вита on 23.06.2017.
 */
public class MainPageTest {
    public WebDriver webDriver;
    MainPage mainPage;
    public LoginPage loginPage;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";
    BrowserVersion browser;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(@Optional ("firefox") String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.getInstance().setup();
            webDriver = new FirefoxDriver();
        } else  if (browser.equalsIgnoreCase("chrome")) {
        ChromeDriverManager.getInstance().setup();
        webDriver = new ChromeDriver();
        }else {
           throw new IllegalArgumentException("The Browser Type is Undefined");}
        webDriver.navigate().to("https://alerts.shotspotter.biz");}

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    @Test
    public void testSwitchIncidentsPeriod() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(Email, Password);
        int[] timeFrameOptions = {24, 3, 7};

        for (int timeFrameOption : timeFrameOptions) {

            mainPage.switchTimeFramePeriod(timeFrameOption);
            int resultsCount = mainPage.getResultsCount();
            int incidentCardsCount = mainPage.getIncidentCardsCount();

            System.out.println("Period:" + timeFrameOption);
            System.out.println("resultsCount: " + resultsCount);
            System.out.println("incidentCardsCount: " + incidentCardsCount);

            Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");
        }
    }

    @DataProvider
    public static Object[][] timeFrameOptions() {
        return new Object[][]{{24}, {3}, {7}};
    }

    @Test(dataProvider = "timeFrameOptions")
    public void testSwitchIncidentsPeriodByDataProvider(int timeFrameOption) {
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(Email, Password);
        mainPage.switchTimeFramePeriod(timeFrameOption);
        int resultsCount = mainPage.getResultsCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();

        System.out.println("Period:" + timeFrameOption);
        System.out.println("resultsCount: " + resultsCount);
        System.out.println("incidentCardsCount: " + incidentCardsCount);

        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");

        Assert.assertTrue(mainPage.getTextDenver().contains("Denver"));
        Assert.assertNotNull(mainPage.getTextTime(), null);
        Assert.assertNotNull(mainPage.getTextAddress(), null);
        Assert.assertFalse(Boolean.parseBoolean(mainPage.getTextTime()), "");
        Assert.assertFalse(Boolean.parseBoolean(mainPage.getTextAddress()), "");
    }




}