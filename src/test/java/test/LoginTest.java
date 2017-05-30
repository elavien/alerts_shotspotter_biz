package test;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import static java.lang.Thread.sleep;

/**
 * Created by Java Script on 20.05.2017.
 */
public class LoginTest {
    public WebDriver webDriver;
    public LoginPage loginPage;

      @BeforeMethod
    public void beforeMethod() {
        webDriver=new FirefoxDriver();
    }

    @AfterMethod
    public void beforeClass(){
         webDriver.quit();
    }

    @Test
    public void testLoginPositive() {
        loginPage=new LoginPage(webDriver);
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://alerts.shotspotter.biz/", "Wrong URL on Login test");
        MainPage mainPage = loginPage.LoginAs("denvert1@shotspotter.net", "Test123!");
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter");
        Assert.assertTrue(webDriver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main"),"Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(), "Settings icon is not displayed");
    }
}
