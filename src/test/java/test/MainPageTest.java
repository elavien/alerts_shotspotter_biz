package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;

import static java.lang.Thread.sleep;

/**
 * Created by SvetLana on 22.06.2017.
 */
public class MainPageTest {
    public WebDriver webDriver;
    public String username = "denvert1@shotspotter.net";
    public String password = "Test123!";

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz");
    }
    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
    @Test
    public void testSwitchIncidentsPeriod() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        MainPage mainPage = loginPage.login(username, password);

        mainPage.selectFrame("7");


       /** mainPage.switchTimeFramePeriod(7);     //home work
        int resultsCount = mainPage.getResultsCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();   //home work

        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count doesn't match Incident Cards count");*/


    }
}
