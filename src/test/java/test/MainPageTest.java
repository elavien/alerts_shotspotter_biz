package test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;


/**
 * Created by Вита on 23.06.2017.
 */
public class MainPageTest {
    public WebDriver webDriver;
    MainPage mainPage;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";
    BrowserVersion browser;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(String browser) {

        if (browser.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
            webDriver = new ChromeDriver();
        }else {
            throw new IllegalArgumentException("The Browser Type is Undefined");}
        webDriver.navigate().to("https://alerts.shotspotter.biz");}

    @AfterMethod
    public void beforeClass() {
        webDriver.quit();
    }

    @Test
    public void testSwitchIncidentsPeriod() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(Email, Password);
        int[] timeFrameOptions = {24, 3, 7};// списое элементов

        for (int timeFrameOption : timeFrameOptions) {// инициализация еще одной переменной, которая является по очереди каждой переменной из списка

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
    }
}