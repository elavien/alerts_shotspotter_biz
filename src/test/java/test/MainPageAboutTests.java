package test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.BasePage;
import page.LoginPage;
import page.MainPage;

import java.util.List;
import java.util.Set;

/**
 * Created by SvetLana on 18.07.2017.
 */
public class MainPageAboutTests {
    public WebDriver webDriver;
    MainPage mainPage;
    public LoginPage loginPage;
    public String Email = "denvert1@shotspotter.net";
    public String Password = "Test123!";
    BrowserVersion browser;

    @Parameters("browser")
    @BeforeMethod
    public void beforeMethod(@Optional("firefox") String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.getInstance().setup();
            webDriver = new FirefoxDriver();
        } else  if (browser.equalsIgnoreCase("chrome")) {
        ChromeDriverManager.getInstance().setup();
        webDriver = new ChromeDriver();
        }else {
           throw new IllegalArgumentException("The Browser Type is Undefined");}
        webDriver.navigate().to("https://alerts.shotspotter.biz");
        LoginPage loginPage = new LoginPage(webDriver);
        mainPage = loginPage.login(Email, Password);}

    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit(); }  }

    @Test
    public void termsOfServis() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.abOutMenuItem();
        mainPage.WebWindoww();}
}
