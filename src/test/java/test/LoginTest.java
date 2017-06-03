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
          loginPage=new LoginPage(webDriver);
    }

    @AfterMethod
    public void beforeClass(){
         webDriver.quit();
    }

    /**@Test
    public void testLoginPositive() {

        Assert.assertEquals(webDriver.getTitle(), "Shotspotter - Login", "Main page title is wrong");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://alerts.shotspotter.biz/", "Wrong URL on Login test");
        MainPage mainPage = loginPage.LoginAs("denvert1@shotspotter.net", "Test123!");
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter");
        Assert.assertTrue(webDriver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main"),"Wrong URL on Main page");
        Assert.assertTrue(mainPage.isPageLoaded(), "Settings icon is not displayed");
    }*/
    /**@Test
    public void testLoginNegative(){
        loginPage.getEmailField().sendKeys("aejhg;lhj@skh.com");
        loginPage.getPasswordField().sendKeys("dafkjgh");
        loginPage.getGObutton().click();
        try {sleep(7000);} catch (InterruptedException e){e.printStackTrace();}
        Assert.assertTrue(loginPage.isError(), "The provided credentials are not correct.");
    }*/
    @Test
    public void NotLogin(){
        MainPage mainPage = loginPage.LoginAs("denvert1@shotspotter.net", "Tesdsst123!");
        Assert.assertFalse(mainPage.isPageLoaded());


    }
}
